package com.inner_code.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal_info")
@Getter
@Setter
@ToString
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    private String sign;
    private String house;

    @Column(name = "main_title")
    private String mainTitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private Integer subscription;

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HealingPlan> healingPlans = new ArrayList<>();

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoreWound> coreWoundsAndEmotionalThemes = new ArrayList<>();

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatternAndStruggle> patternsAndStruggles = new ArrayList<>();

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealingAndTransformation> healingAndTransformations = new ArrayList<>();

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpiritualWisdomAndGift> spiritualWisdomAndGifts = new ArrayList<>();

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WoundPoint> woundPoints = new ArrayList<>();

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatternConnectedToWound> patternsConnectedToThisWound = new ArrayList<>();

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealingBenefit> healingBenefits = new ArrayList<>();

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReflectiveQuestion> reflectiveQuestions = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();
}
