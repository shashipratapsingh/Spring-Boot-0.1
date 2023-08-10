package com.dev.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyName;
    private String companyAddress;
}
