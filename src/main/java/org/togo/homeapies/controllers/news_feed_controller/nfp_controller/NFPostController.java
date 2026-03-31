package org.togo.homeapies.controllers.news_feed_controller.nfp_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togo.homeapies.d_t_o_s.news_feed_dto.nf_post_dto.NFPostDto;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;
import org.togo.homeapies.entities.user_entity.UserEntity;
import org.togo.homeapies.repos.news_feed_repo.nfp_repo.NFPostRepo;
import org.togo.homeapies.services.news_feed_service.nfp_service.NFPostService;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
=======
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.togo.homeapies.custom_context.cc_user.CustomUserDetails;
import org.togo.homeapies.d_t_o_s.news_feed_dto.nf_post_dto.NFAllPostDto;
import org.togo.homeapies.d_t_o_s.news_feed_dto.nf_post_dto.NFPostDto;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;
import org.togo.homeapies.repos.news_feed_repo.nfp_repo.NFPostRepo;
import org.togo.homeapies.services.news_feed_service.nfp_service.NFPostService;
import java.util.Collections;
import java.util.List;
>>>>>>> d2b5cde (added craete post, exchange access token with refresh_token)


@RestController
@RequestMapping("/newsfeedpost")
@RequiredArgsConstructor
public class NFPostController {

    private final NFPostService postService;
    private final NFPostRepo postRepo;

    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<HttpStatus> createPostViaUser(@RequestBody NFPostDto nfPostDto) throws MalformedURLException {
        UserEntity isUserFound = postService.findUserViaId(nfPostDto.getUserId());
        boolean text = nfPostDto.getPostText().isBlank();
        boolean imgLink = nfPostDto.getImgUrl().trim().isBlank();
        if (isUserFound == null){
           return new ResponseEntity<>(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
        }else {
            LocalDateTime localDateTime = LocalDateTime.now();
            NFPostEntity postEntity = new NFPostEntity();
            postEntity.setUserEntity(isUserFound);
            postEntity.setCreateAt(localDateTime);
            postEntity.setUpdateAt(localDateTime);
            postRepo.save(postEntity);
            if (!text && !imgLink){
                postService.savePostTextViaService(nfPostDto, postEntity);
                postService.savePostImgViaService(nfPostDto, postEntity);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else {
                if (!text) postService.savePostTextViaService(nfPostDto, postEntity);
                if (!imgLink) postService.savePostImgViaService(nfPostDto, postEntity);
                if (text && imgLink) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
=======
    public ResponseEntity<HttpStatus> createPostViaUser(@RequestBody NFPostDto nfPostDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof CustomUserDetails)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
>>>>>>> d2b5cde (added craete post, exchange access token with refresh_token)
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


}
