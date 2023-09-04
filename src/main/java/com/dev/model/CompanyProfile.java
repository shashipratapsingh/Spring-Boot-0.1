package com.dev.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table
@Data
@NoArgsConstructor
//@AllArgsConstructor
@Setter
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String companyName;
    private String companyAddress;

    public CompanyProfile(int id, String companyName, String companyAddress) {
        this.id = id;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }


}
