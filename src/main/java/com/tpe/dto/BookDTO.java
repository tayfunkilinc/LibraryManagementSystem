package com.tpe.dto;

import com.tpe.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
	@NotBlank(message = "Kitap ismi bosluk olamaz!!!")
	@NotNull(message = "Kitap ismi girilmelidir!!!")
	@Size(min = 2, max = 50, message = "Kitap ismi en az 2 en fazla 50 karakter icermelidir!!!")
	@Column(nullable = false)
	private String title;

	@NotBlank(message = "Yazar ismi bosluk olamaz!!!")
	@Size(min = 2, max = 50, message = "Yazar ismi en az 2 en fazla 50 karakter icermelidir!!!")
	@Column(nullable = false)
	private String author;

	@NotBlank(message = "Lütfen yayin yilini giriniz!!!")
	@Column(nullable = false)
	private String publicationDate;

	//book-->bookDTO
	public BookDTO(Book book) {
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.publicationDate = book.getPublicationDate();
	}
}
