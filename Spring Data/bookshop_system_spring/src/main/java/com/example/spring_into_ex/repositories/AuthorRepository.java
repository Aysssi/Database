package com.example.spring_into_ex.repositories;

import com.example.spring_into_ex.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

   @Query("SELECT a FROM Author  as a ORDER BY a.books.size DESC")
    List<Author> findAuthorByCountOfBook();
}
