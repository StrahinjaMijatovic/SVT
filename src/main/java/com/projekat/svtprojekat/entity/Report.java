package com.projekat.svtprojekat.entity;

import com.projekat.svtprojekat.entity.enums.ReportReason;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ReportReason reason;
    @Column(nullable = false)
    private LocalDate timestamp;

    @Column()
    private boolean isAccepted;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private Report report;

}
