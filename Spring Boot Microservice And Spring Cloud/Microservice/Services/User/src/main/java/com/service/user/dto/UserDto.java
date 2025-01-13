package com.service.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponseModel> albums;
}
