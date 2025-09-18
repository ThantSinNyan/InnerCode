package com.inner_code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prompt")
@Getter
@Setter
public class Prompt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "healing_plan_id", nullable = false)
    @JsonIgnore
    private HealingPlan healingPlan;
}
