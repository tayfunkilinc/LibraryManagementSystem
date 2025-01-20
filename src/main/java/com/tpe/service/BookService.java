package com.tpe.service;


import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.exception.BookNotFoundException;
import com.tpe.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor // bu sadece final keywordlu contructorlarin uretimini yapar ve enjekte eder
public class BookService {

    private final BookRepository bookRepository;

    //1-b
    public void saveBook(@Valid BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationDate(bookDTO.getPublicationDate());
        bookRepository.save(book);
    }

    //2-b
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    //3-b
    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found by id: " + id));
        return book;
    }

    //3-c
    public BookDTO getBookDTOById(Long id) {
        Book book = getBookById(id);
        return new BookDTO(book);
        //alternatif: repositoryde JPQL ile dogrudan DTO objesi de döndürebiliriz.
    }

    //4-b
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
    //6-b
    public List<Book> filterBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Page<Book> getBookByPage(Pageable pageable) {
        return  bookRepository.findAll(pageable);
    }
    //8-b
    public void updateBook(Long id, @Valid BookDTO bookDTO) {
        Book foundBook = getBookById(id);
        foundBook.setTitle(bookDTO.getTitle());
        foundBook.setAuthor(bookDTO.getAuthor());
        foundBook.setPublicationDate(bookDTO.getPublicationDate());
        bookRepository.save(foundBook);  //merge: update ... set ...
    }
    //9-b
    public List<Book> filterBooksByAuthor(String author) {
        List<Book> bookList =bookRepository.findByAuthorWithJPQL(author);
        if (bookList.isEmpty()){
            throw new BookNotFoundException("Yazara Ait Kitap Bulunamadi...");
        }
        return bookList;
    }
}
