package org.togo.homeapies.d_t_o_s.user_dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String monoUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private String numCountryCode;
    private List<String> role;

}
