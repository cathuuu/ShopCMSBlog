package com.example.ShopCMSBlog.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "blog_comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    PostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @Column(name = "content", columnDefinition = "TEXT")
    String content;

    @Column(name = "created_at")
    LocalDateTime createdAt = LocalDateTime.now();
}
