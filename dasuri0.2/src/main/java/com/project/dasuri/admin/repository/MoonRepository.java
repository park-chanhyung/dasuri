package com.project.dasuri.admin.repository;

import com.project.dasuri.admin.entity.FaqEntity;
import com.project.dasuri.admin.entity.MoonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoonRepository extends JpaRepository<MoonEntity, Long> {
}
