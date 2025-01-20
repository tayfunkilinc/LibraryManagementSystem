package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "t_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Kitap Ismi Bosluk Olmaz!!!")
    @NotBlank(message = "Kitap Ismi Girilmelidir!!")
    @Size(min = 2, max = 50, message = "Kitap Ismi En Az Iki Karakter Icermelidir!!!")
    @Column(nullable = false) //isim bos girilemez ama service'de falan kendimiz hatali gonderim yapabiliriz bunun icin db'ye kayittada bu kontrolu yapmaliyiz
    private String title;

    @NotBlank(message = "Yazar ismi Bosluk Olamaz!!1")
    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "Yazar Ismi En Az Iki Karakter Icermelidir!!!")
    private String author;

    @NotBlank(message = "Lutfen Yayin Tarihini Giriniz")
    @Column(nullable = false)
    String publicationDate;

    @ManyToOne //fk ekler buraya many olan tarafa
    @JsonIgnore
    private Owner owner;

}
