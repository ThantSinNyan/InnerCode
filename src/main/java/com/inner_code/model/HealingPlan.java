package com.inner_code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "healing_plan")
@Getter
@Setter
public class HealingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private String activity;

    private String meditation;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String affirmation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meditation_media_id")
    private MeditationMedia meditationMedia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_info_id", nullable = false)
    @JsonIgnore
    private PersonalInfo personalInfo;

    @OneToMany(mappedBy = "healingPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prompt> prompts = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
