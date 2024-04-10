package com.example.demo.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostRequestDto {
    private long userId;
    private String content;
}
