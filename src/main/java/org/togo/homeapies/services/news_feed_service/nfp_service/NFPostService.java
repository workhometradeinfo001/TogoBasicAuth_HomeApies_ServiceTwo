package org.togo.homeapies.services.news_feed_service.nfp_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.togo.homeapies.d_t_o_s.news_feed_dto.nf_post_dto.NFPostDto;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.nfp_sub_entity.NFPostImgEntity;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.nfp_sub_entity.NFPostTextEntity;
import org.togo.homeapies.entities.user_entity.UserEntity;
import org.togo.homeapies.repos.news_feed_repo.nfp_repo.NFPostImgRepo;
import org.togo.homeapies.repos.news_feed_repo.nfp_repo.NFPostTextRepo;
import org.togo.homeapies.repos.user_repo.mysql_repo.MysqlUserRepo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NFPostService {

    private final MysqlUserRepo userRepo;
    private final NFPostTextRepo postTextRepo;
    private final NFPostImgRepo postImgRepo;

    public UserEntity findUserViaId(long id){
         return userRepo.findById(id)
                 .orElse(null);
    }

    public void savePostTextViaService(NFPostDto postDto, NFPostEntity postEntity){
        NFPostTextEntity textEntity = new NFPostTextEntity();
        //PostText field....
        textEntity.setPostText(postDto.getPostText());
        textEntity.setNfPostText(postEntity);
<<<<<<< HEAD
        textEntity.setUserEntity(postEntity.getUserEntity());
        textEntity.setUpdateAt(postEntity.getUpdateAt());
        textEntity.setCreateAt(postEntity.getCreateAt());
=======
//        textEntity.setUserEntity(postEntity.getUserEntity());
>>>>>>> d2b5cde (added craete post, exchange access token with refresh_token)
        postTextRepo.save(textEntity);
    }
    public void savePostImgViaService(NFPostDto postDto, NFPostEntity postEntity) throws MalformedURLException {
        byte[] imgBytes = urlToLongBlobImg(postDto.getImgUrl());

        NFPostImgEntity imgEntity = new NFPostImgEntity();
        //ImageEntity field
        imgEntity.setPostImg(imgBytes);
        imgEntity.setNfPostImg(postEntity);
<<<<<<< HEAD
        imgEntity.setUserEntity(postEntity.getUserEntity());
        imgEntity.setCreateAt(postEntity.getCreateAt());
        imgEntity.setUpdateAt(postEntity.getUpdateAt());
=======
//        imgEntity.setUserEntity(postEntity.getUserEntity());
>>>>>>> d2b5cde (added craete post, exchange access token with refresh_token)
        postImgRepo.save(imgEntity);
    }
    public byte[] urlToLongBlobImg(String image) throws MalformedURLException{
        URL imgUrl = new URL(image);
        try(InputStream inputStream = imgUrl.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ){
            //Buffer for img
            byte[] buffer = new byte[4096];
            int byteReads;
            while ((byteReads = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, byteReads);
            }
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
