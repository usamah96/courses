package com.service.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlbumResponseModel {

    private String albumId;
    private String userId;
    private String name;
    private String description;
}
