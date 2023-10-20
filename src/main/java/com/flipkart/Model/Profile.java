package com.flipkart.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 25 characters")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Temporal(TemporalType.DATE) // Store only the date part, not time
    private Date dob;
    @NotNull(message = "Mobile number is required")
    @Min(value = 1000000000L, message = "Mobile number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
    private Long mobileNo;

    @CreationTimestamp
    private Date CreatedAt;
    @UpdateTimestamp
    private Date UpdatedAt;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "profile_id")
    private List<ManageAddress> manageAddress;

  /*  @OneToMany(targetEntity = ManageAddress.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk" ,referencedColumnName ="id")
    private List<ManageAddress> manageAddress;*/
}
