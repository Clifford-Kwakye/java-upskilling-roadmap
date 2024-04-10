package com.example.demo.posts.service;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.posts.dto.AllPostDto;
import com.example.demo.posts.dto.PostRequestDto;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService implements IPost {
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Override
  public AllPostDto getPosts(int page, int limit) {
    Pageable pageable = PageRequest.of(page - 1, limit);
    Page<Post> posts = postRepository.findAll(pageable);

    return AllPostDto.builder().posts(posts.getContent()).totalPages(posts.getTotalPages()).build();
  }

  @Override
  public AllPostDto getUserPosts(int page, int limit, int uuid) {
    Pageable pageable = PageRequest.of(page - 1, limit);
    Page<Post> posts = postRepository.findAll(pageable);

    return AllPostDto.builder().posts(posts.getContent()).totalPages(posts.getTotalPages()).build();
  }

  @Override
  public Post createPost(PostRequestDto postRequestDto) {
    User user =
        userRepository
            .findById(postRequestDto.getUserId())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return postRepository.save(
        Post.builder().content(postRequestDto.getContent()).createdBy(user).build());
  }
}
