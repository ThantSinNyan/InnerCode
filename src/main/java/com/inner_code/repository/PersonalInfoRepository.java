package com.inner_code.repository;

import com.inner_code.model.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    List<PersonalInfo> findByUserId(Long userId);
}
