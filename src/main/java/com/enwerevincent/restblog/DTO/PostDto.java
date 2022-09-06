package com.enwerevincent.restblog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private String title ;
    private String description;
    private String featuredImage;
    private int user_id;

}
