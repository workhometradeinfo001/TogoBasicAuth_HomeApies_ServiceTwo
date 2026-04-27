package org.togo.homeapies.services.news_feed_service.nfp_service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.stereotype.Service;
import org.togo.homeapies.d_t_o_s.news_feed_dto.nf_post_dto.NFPostDto;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.nfp_sub_entity.NFPostImgEntity;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.nfp_sub_entity.NFPostTextEntity;
import org.togo.homeapies.entities.user_entity.UserEntity;
import org.togo.homeapies.project_interfaces.post_inter.PostViewInter;
import org.togo.homeapies.records.post_record.PostRecordDto;
import org.togo.homeapies.records.post_record.ScrollResponse;
import org.togo.homeapies.records.post_record.UserNameRecord;
import org.togo.homeapies.repos.news_feed_repo.nfp_repo.NFPostImgRepo;
import org.togo.homeapies.repos.news_feed_repo.nfp_repo.NFPostRepo;
import org.togo.homeapies.repos.news_feed_repo.nfp_repo.NFPostTextRepo;
import org.togo.homeapies.repos.user_repo.mysql_repo.MysqlUserRepo;
import org.togo.homeapies.repos.user_repo.user_interface.UserEntityRepo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NFPostService {

    private final MysqlUserRepo mysqlUserRepo;
    private final NFPostTextRepo postTextRepo;
    private final NFPostImgRepo postImgRepo;
    private final NFPostRepo postRepo;
    private final UserEntityRepo userRepo;

    public UserEntity findUserViaId(long id){
         return mysqlUserRepo.findById(id)
                 .orElse(null);
    }

    public void savePostTextViaService(NFPostDto postDto, NFPostEntity postEntity){
        NFPostTextEntity textEntity = new NFPostTextEntity();
        //PostText field....
        textEntity.setPostText(postDto.getPostText());
        textEntity.setNfPostText(postEntity);
        postTextRepo.save(textEntity);
    }
    public void savePostImgViaService(NFPostDto postDto, NFPostEntity postEntity) {
        NFPostImgEntity imgEntity = new NFPostImgEntity();
        //ImageEntity field
        imgEntity.setPostImg(postDto.getImgUrl());
        imgEntity.setNfPostImg(postEntity);
        postImgRepo.save(imgEntity);
    }

    public ScrollResponse<PostRecordDto> getPostWindow(ScrollPosition position, int limit){
        Window<PostViewInter> postData = postRepo.findByOrderByCreateAtDesc(
                position, Limit.of(limit));

        List<PostRecordDto> cleanList = postData.getContent().stream()
                .map(post -> new PostRecordDto(
                        post.getId(),
                        post.getWhoCreated(),
                        post.getPostText() == null ? "" : post.getPostText(),
                        post.getPostImg() == null ? "" : post.getPostImg(),
                        post.getFirstName() == null ? userName("fn", post.getWhoCreated()).orElse("Anonymous") : post.getFirstName(),
                        post.getLastName() == null ? userName("ln", post.getWhoCreated()).orElse("Person") : post.getLastName(),
                        post.getCreateAt()
                ))
                .toList();
        String nextToken = null;
        if (!postData.isEmpty()){
            ScrollPosition lastPos = postData.positionAt(postData.size() - 1);
            if (lastPos instanceof KeysetScrollPosition keysetSc){
                Object object = keysetSc.getKeys().get("createAt");
                PostRecordDto postRecordDto = cleanList.get(cleanList.size() - 1);
                Long id = postRecordDto.id();
                nextToken = object.toString()+ ","+id;
            }
        }
        return new ScrollResponse<>(cleanList, nextToken, postData.hasNext());
    }

   public Optional<String> userName(String nameHint, Long id){
       Optional<UserNameRecord> name = userRepo.findFirstNameAndLastNameByCreateBy(id);
       if (Objects.equals(nameHint, "fn")){
            return name.map(UserNameRecord::firstName);
        }
       if (Objects.equals(nameHint, "ln")){
           return name.map(UserNameRecord::lastName);
       }
       return Optional.empty();
   }

}
