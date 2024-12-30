package com.jakartadata.repository;

import com.jakartadata.model.Comment;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Update;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment, UUID> {

  @Insert
  void addComment(Comment comment);

  @Delete
  void removeComment(Comment comment);

  @Update
  void updateComment(Comment comment);

  @Find
  Optional<Comment> byId(UUID id);

  @Query("FROM Comment c where c.createdAt = ?1")
  List<Comment> byCreatedAt(LocalDateTime createdAt);

  @Find
  @OrderBy("createdAt")
  List<Comment> allComments();

  @Query("where content like :content")
  List<Comment> findByContentLike(String content);
}
