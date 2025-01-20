package com.tpe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Owner { //one -> many

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Gecerli Bir Deger Giriniz!!")
    @Column(nullable = false)
    private  String name;

    @NotBlank(message = "Gecerli Bir Deger Giriniz!!")
    @Column(nullable = false)
    private String lastName;


    private String phoneNumber;

    @Email(message = "Gecerli Bir Deger Giriniz!!")
    @Column(nullable = false, unique = true)
    private String email;

    @Setter(AccessLevel.NONE)
    private LocalDateTime registrationDate;

    @PrePersist //objeyi kaydetmeden hemen once bu metod cagrilir
    public void setRegistrationDateAuto(){
        this.registrationDate = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "owner") //burda fk olusturma diger tarafta kullanilan iliskiyi referans al
    private List<Book> bookList = new ArrayList<>();
}
