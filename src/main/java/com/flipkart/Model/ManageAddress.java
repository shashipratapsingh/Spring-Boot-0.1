package com.flipkart.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Entity
@Table(name = "manageAddress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManageAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    @NotNull(message = "Mobile number is required")
    @Min(value = 1000000000L, message = "Mobile number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
    private Long mobileNumber;

    @NotNull(message = "Pin code is required")
    @Digits(integer = 6, fraction = 0, message = "Pin code must be numeric and have 6 digits")
    private Long pinCode;

    @NotBlank(message = "Locality is required")
    private String locality;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    private String landmark;

    private String alternatePhoneNo;

    @NotBlank(message = "Address type is required")
    private String addressType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
