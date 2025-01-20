package com.tpe.repository;

import com.tpe.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //opsiyonel
public interface BookRepository extends JpaRepository<Book, Long> {

    //6-c
    List<Book> findByTitle(String title); //select * from t_book where title=:title

    //9-c
    @Query("SELECT b FROM Book b WHERE b.author=:yazar")
    List<Book> findByAuthorWithJPQL(@Param("yazar") String author);
}
