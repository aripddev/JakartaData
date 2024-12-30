package com.jakartadata.service;

import com.jakartadata.model.Comment;
import com.jakartadata.repository.CommentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CommentService {

  @Inject
  CommentRepository repository;

  @Transactional
  public void createComment(Comment comment) {
    repository.addComment(comment);
  }

  @Transactional
  public Response deleteCommentById(UUID id) {
    Optional<Comment> comment = repository.byId(id);
    if (comment.isPresent()) {
      repository.removeComment(comment.get());
      return Response.noContent().build();
    }
    return Response.status(404).build();
  }

  public Response findCommentById(UUID id) {
    Optional<Comment> comment = repository.byId(id);
    return comment.map(c -> Response.ok(c).build())
            .orElse(Response.status(404).build());
  }

  @Transactional
  public Response updateComment(UUID id, Comment updatedComment) {
    Optional<Comment> existingComment = repository.byId(id);
    if (existingComment.isPresent()) {
      Comment comment = existingComment.get();
      comment.setId(updatedComment.getId());
      comment.setContent(updatedComment.getContent());
      repository.updateComment(comment);
      return Response.ok(comment).build(); // Return updated comment
    }
    return Response.status(404).build(); // Return 404 if comment not found
  }

  public List<Comment> findAllComments() {
    return repository.allComments();
  }

  public List<Comment> findAllCommentsByCreatedAt(LocalDateTime createdAt) {
    return repository.byCreatedAt(createdAt);
  }
}
