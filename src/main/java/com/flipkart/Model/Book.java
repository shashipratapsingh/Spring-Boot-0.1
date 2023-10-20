package com.flipkart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name ="stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Book Id is required")
    private String bookRegisterId;
    @NotNull(message = "Book name is required")
    private String bookName;
    @NotNull(message = "Book type is required")
    private String bookType;
    @NotNull(message = "author name is required")
    private String authorName;
    @NotNull(message = "publication is required")
    private String publication;
    private int quantity;
    @JsonIgnore
    private String addedBy;
    @CreationTimestamp
    private Date issueDate;
    @CreationTimestamp
    private Date CreatedAt;
    @UpdateTimestamp
    private Date UpdatedAt;




}
