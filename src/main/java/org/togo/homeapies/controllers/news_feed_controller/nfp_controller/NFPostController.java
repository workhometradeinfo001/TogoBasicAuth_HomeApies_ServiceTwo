package org.togo.homeapies.controllers.news_feed_controller.nfp_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/newsfeedpost")
@RequiredArgsConstructor
public class NFPostController {

    private final NFPostService postService;
    private final NFPostRepo postRepo;

    @PostMapping
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
        }
    }


}
