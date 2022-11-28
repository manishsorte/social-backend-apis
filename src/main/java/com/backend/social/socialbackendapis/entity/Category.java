package com.backend.social.socialbackendapis.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int category_Id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> listOfPosts = new ArrayList<>();

}
