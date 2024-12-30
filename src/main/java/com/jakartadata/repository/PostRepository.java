package com.jakartadata.repository;

import com.jakartadata.model.Post;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Update;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {

  @Insert
  void addPost(Post post);

  @Delete
  void removePost(Post post);

  @Update
  void updatePost(Post post);

  @Find
  Optional<Post> byId(UUID id);

  @Query("FROM Post p where p.title = ?1")
  List<Post> byTitle(String title);

  @Find
  @OrderBy("lastModifiedAt")
  List<Post> allPosts();

  @Query("where content like :content")
  List<Post> findByContentLike(String content);
}
