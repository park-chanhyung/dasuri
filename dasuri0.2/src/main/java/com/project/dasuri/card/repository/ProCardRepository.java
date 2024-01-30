package com.project.dasuri.card.repository;

import com.project.dasuri.member.entity.ProEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProCardRepository extends JpaRepository<ProEntity,String> {
    Optional<ProEntity> findByProId(String ooo);
}
