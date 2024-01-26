package com.project.dasuri.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pro_table")
public class ProEntity {
    @Id
    private String proId;
    @Column
    private String proName;
    @Column
    private String proPwd;
    @Column
    private String proPhone;
    @Column
    private String proLegions;

    @CreatedDate
    private LocalDateTime signupDate;

}
