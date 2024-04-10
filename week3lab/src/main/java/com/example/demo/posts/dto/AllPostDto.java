package com.example.demo.posts.dto;

import com.example.demo.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllPostDto {
    private List<Post> posts;
    private int totalPages;
}
