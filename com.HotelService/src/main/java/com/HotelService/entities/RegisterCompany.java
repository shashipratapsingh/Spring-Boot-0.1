package com.HotelService.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "companyDetails")
public class RegisterCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registrationId;
    @NotEmpty
    @Size(min = 5, message = "company name should have at least 5 characters")
    private String companyName;
    @NotEmpty
    @Size(min = 5, message = "company Address should have at least 5 characters")
    private String companyAddress;
    @NotEmpty
    @Size(min = 5, message = "company Type should have at least 5 characters")
    private String companyType;
    @NotEmpty
    @Size(min = 5, message = "company Owner Name should have at least 5 characters")
    private String companyOwnerName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;


    @CreationTimestamp
    private Date createdBy;
    @UpdateTimestamp
    private Date updatedBy;
}
