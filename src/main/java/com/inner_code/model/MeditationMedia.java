package com.inner_code.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meditation_media")
@Getter
@Setter
public class MeditationMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgLink;
    private String videoLink;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "meditationMedia", cascade = CascadeType.ALL, orphanRemoval = false,fetch = FetchType.LAZY)
    private List<HealingPlan> healingPlans;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
