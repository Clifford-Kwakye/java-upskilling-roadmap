package com.example.demo.posts.service;

import com.example.demo.entities.Post;
import com.example.demo.posts.dto.AllPostDto;
import com.example.demo.posts.dto.PostRequestDto;

public interface IPost {
    AllPostDto getPosts(int page, int limit);
    AllPostDto getUserPosts(int page, int limit, int uuid);

    Post createPost(PostRequestDto postRequestDto);
}
