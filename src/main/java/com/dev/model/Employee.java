package com.dev.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@JsonProperty("full_name")
   // @NotNull(message = "age should not be null")
   // @NotEmpty(message = "age should not be null")
    @NotBlank(message = "age should not be null")  // it will check null value , empty value and charector value
    private  String name;
   /* @JsonIgnore*/
    private  int age;
    private String location;
    @Email(message = "please enter the valid email")
    private String email;
    //@NotEmpty(message = "age should not be null")
    @NotBlank(message = "Age Should not be null")
    private String department;
    @CreationTimestamp
    @Column(name = "create_at", nullable = false,updatable = false)
    private  Date createAt;
    @CreationTimestamp
    @Column(name = "update_at", nullable = false,updatable = false)
    private Date updateAt;


   /* @JoinColumn(name = "empSalary_id")
    @OneToOne
    private EmpSalarys empSalary;*/

}


