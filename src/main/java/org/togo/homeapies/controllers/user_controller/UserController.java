package org.togo.homeapies.controllers.user_controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togo.homeapies.d_t_o_s.mtmdt_dto.EmailDto;
import org.togo.homeapies.d_t_o_s.user_dto.UserDto;
import org.togo.homeapies.services.mongo_service.MyMongoService;
import org.togo.homeapies.services.mysql_user_service.MysqlUserService;

@RestController
@RequestMapping("/mtmdt")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final MyMongoService mongoService;
    private final MysqlUserService mysqlUserService;

    @PostMapping
    public ResponseEntity<HttpStatus> dataMoving(@RequestBody EmailDto emailDto){
        try{
            UserDto dataPresent = mongoService.isDataPresent(emailDto.getEmail());
            if (dataPresent != null){
                boolean response = mysqlUserService.insertDataSql(dataPresent);
                if (response){
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
