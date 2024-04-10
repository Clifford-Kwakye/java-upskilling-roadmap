package com.example.demo.posts.controller;

import com.example.demo.entities.Post;
import com.example.demo.posts.dto.AllPostDto;
import com.example.demo.posts.dto.PostRequestDto;
import com.example.demo.posts.service.IPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
  private final IPost post;

  @GetMapping
  public ResponseEntity<AllPostDto> getPosts(
      @RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit) {
    return new ResponseEntity<>(post.getPosts(page, limit), HttpStatus.OK);
  }

  @GetMapping("/user")
  public ResponseEntity<AllPostDto> getUserPosts(
      @RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
      @RequestParam(name = "uuid") int uuid) {
    return new ResponseEntity<>(post.getUserPosts(page, limit, uuid), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Post> createPost(@RequestBody PostRequestDto postRequestDto) {
    return new ResponseEntity<>(post.createPost(postRequestDto), HttpStatus.CREATED);
  }
}
