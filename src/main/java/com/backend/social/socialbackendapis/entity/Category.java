package com.backend.social.socialbackendapis.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
