package com.backend.social.socialbackendapis.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PostDto {

    private int postId;
    private String title;
    private String imageName;
    private String content;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
    private List<CommentDto> comment = new ArrayList<>();
}

