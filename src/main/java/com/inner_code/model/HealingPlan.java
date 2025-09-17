package com.inner_code.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_info_id", nullable = false, unique = true)
    private PersonalInfo personalInfo;

    @OneToMany(mappedBy = "healingPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prompt> prompts = new ArrayList<>();
}
