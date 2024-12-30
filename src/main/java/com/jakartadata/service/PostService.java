package com.jakartadata.service;

import com.jakartadata.model.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import com.jakartadata.repository.PostRepository;
import java.util.UUID;

@ApplicationScoped
public class PostService {

  @Inject
  PostRepository repository;

  @Transactional
  public void createPost(Post post) {
    repository.addPost(post);
  }

  @Transactional
  public Response deletePostById(UUID id) {
    Optional<Post> post = repository.byId(id);
    if (post.isPresent()) {
      repository.removePost(post.get());
      return Response.noContent().build();
    }
    return Response.status(404).build();
  }

  public Response findPostById(UUID id) {
    Optional<Post> post = repository.byId(id);
    return post.map(c -> Response.ok(c).build())
            .orElse(Response.status(404).build());
  }

  @Transactional
  public Response updatePost(UUID id, Post updatedPost) {
    Optional<Post> existingPost = repository.byId(id);
    if (existingPost.isPresent()) {
      Post post = existingPost.get();
      post.setTitle(updatedPost.getTitle());
      post.setContent(updatedPost.getContent());
      repository.updatePost(post);
      return Response.ok(post).build(); // Return updated post
    }
    return Response.status(404).build(); // Return 404 if post not found
  }

  public List<Post> findAllPosts() {
    return repository.allPosts();
  }

  public List<Post> findAllPostsByTitle(String title) {
    return repository.byTitle(title);
  }
}
