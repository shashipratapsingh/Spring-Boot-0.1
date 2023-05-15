package com.kisaan.jai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "CropExpertMapping",
        uniqueConstraints = { @UniqueConstraint(name = "CropExpert", columnNames = { "expert_id", "crop_id" }) })
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropExpertMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ZoomConsultant expert;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Crop crop;
}
