package com.inner_code.repository;

import com.inner_code.model.PersonalInfo;
import com.inner_code.model.ReflectiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReflectiveQuestionRepository extends JpaRepository<ReflectiveQuestion, Long> {
    Optional<ReflectiveQuestion> findById(Long id);
    List<ReflectiveQuestion> findByPersonalInfoId(Long personalInfoId);
}
