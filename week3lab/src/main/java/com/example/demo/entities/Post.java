package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column
    private String content;

    @Column
    private long likes;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User createdBy;
}
