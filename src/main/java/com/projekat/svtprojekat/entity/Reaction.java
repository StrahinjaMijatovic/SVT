package com.projekat.svtprojekat.entity;

import com.projekat.svtprojekat.entity.enums.ReactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ReactionType type;

    @Column(nullable = false)
    private LocalDate timestamp;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private Post post;

    @ManyToOne()
    private Comment comment;

    public Reaction() {
    }
}
