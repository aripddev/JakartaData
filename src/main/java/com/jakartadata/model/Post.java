package com.jakartadata.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Basic(optional = false)
  String title;

  @Basic(optional = false)
  String content;

  @Enumerated
  @Builder.Default
  Status status = Status.DRAFT;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  // cascade does not work in StatelessSession
  // see:https://docs.jboss.org/hibernate/orm/6.6/repositories/html_single/Hibernate_Data_Repositories.html#programming-model
  @OnDelete(action = OnDeleteAction.CASCADE)
  List<Comment> comments;

  @CreationTimestamp
  LocalDateTime createdAt;

  @UpdateTimestamp
  LocalDateTime lastModifiedAt;

  @Override
  public String toString() {
    return "Post{"
            + "id=" + id
            + ", title='" + title + '\''
            + ", content='" + content + '\''
            + ", createdAt=" + createdAt
            + ", lastModifiedAt=" + lastModifiedAt
            + '}';
  }
}
