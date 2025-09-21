package com.inner_code.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "reflective_question")
@Getter
@Setter
public class ReflectiveQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false)
    private Integer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_info_id", nullable = false)
    private PersonalInfo personalInfo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
