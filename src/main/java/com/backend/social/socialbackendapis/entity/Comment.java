package com.backend.social.socialbackendapis.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
