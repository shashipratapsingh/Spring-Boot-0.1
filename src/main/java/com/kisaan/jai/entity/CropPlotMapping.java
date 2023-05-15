package com.kisaan.jai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "CropPlotMapping",
        uniqueConstraints = { @UniqueConstraint(name = "CropPlot", columnNames = { "plot_id", "crop_id" }) })
@Setter
@Getter
@NoArgsConstructor
public class CropPlotMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Plot plot;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Crop crop;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Farmer farmer;
}
