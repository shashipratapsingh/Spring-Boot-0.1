package com.dev.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Entity
@Data
@Table(name="emails1")
public class Emails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String to1;
    private String subject1;
    private String body1;
    private LocalDateTime expirationTime;

    public Emails(Long id, String to1, String subject1, String body1, LocalDateTime expirationTime) {
        this.id = id;
        this.to1 = to1;
        this.subject1 = subject1;
        this.body1 = body1;
        this.expirationTime = expirationTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTo1(String to1) {
        this.to1 = to1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public void setBody1(String body1) {
        this.body1 = body1;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "Emails{" +
                "id=" + id +
                ", to1='" + to1 + '\'' +
                ", subject1='" + subject1 + '\'' +
                ", body1='" + body1 + '\'' +
                ", expirationTime=" + expirationTime +
                '}';
    }


}
