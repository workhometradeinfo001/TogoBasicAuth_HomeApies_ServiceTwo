package org.togo.homeapies.controllers.news_feed_controller.nfp_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.togo.homeapies.custom_context.cc_user.CustomUserDetails;
import org.togo.homeapies.d_t_o_s.news_feed_dto.nf_post_dto.NFPostDto;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;
import org.togo.homeapies.project_interfaces.post_inter.PostViewInter;
import org.togo.homeapies.records.post_record.PostRecordDto;
import org.togo.homeapies.records.post_record.ScrollResponse;
import org.togo.homeapies.repos.news_feed_repo.nfp_repo.NFPostRepo;
import org.togo.homeapies.services.news_feed_service.nfp_service.NFPostService;

import java.time.Instant;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/newsfeedpost")
@RequiredArgsConstructor
@Slf4j
public class NFPostController {

    private final NFPostService postService;
    private final NFPostRepo postRepo;

    @PostMapping
    public ResponseEntity<HttpStatus> createPostViaUser(@RequestBody NFPostDto nfPostDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof CustomUserDetails)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 2. Data Validation
        boolean hasText = !nfPostDto.getPostText().isBlank();
        boolean hasImg = !nfPostDto.getImgUrl().isBlank();
        if (!hasText && !hasImg) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Nothing to save
        }
        // 3. Save Entity (Auditing takes care of createdBy)
        NFPostEntity postEntity = new NFPostEntity();
        postRepo.save(postEntity);
        // 4. Save Content
        if (hasText) postService.savePostTextViaService(nfPostDto, postEntity);
        if (hasImg) postService.savePostImgViaService(nfPostDto, postEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ScrollResponse<PostRecordDto> extractPosts(
            @RequestParam(required = false) String token,
            @RequestParam(defaultValue = "5") int limit
    ){
        try {
            ScrollPosition position;
            if (token != null && token.contains(",")){
                String[] parts = token.split(",");
                String timestamp = parts[0];
                String id = parts[1];

                position = ScrollPosition.of(Map.of(
                        "createAt", Instant.parse(timestamp),
                        "id", Long.parseLong(id)
                ), ScrollPosition.Direction.FORWARD);
            }else {
                position = ScrollPosition.keyset();
            }
            return postService.getPostWindow(position, limit);
        }catch (Exception e){
            log.error("Didn't extract posts!", e);
            return new ScrollResponse<>(List.of(), null, false);
        }
    }

}
