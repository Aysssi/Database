package com.softuni.springintroex.domain.repositories;

import com.softuni.springintroex.domain.entities.AgeRestriction;
import com.softuni.springintroex.domain.entities.Book;
import com.softuni.springintroex.domain.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    //select b FROM BOOK b WHERE b.ageRestriction =  :ageRestriction
    Set<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    Set<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);


  //  @Query("select b FROM Book b WHERE b.price NOT BETWEEN 5 and 40")
    Set<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound,BigDecimal upperBound);

    @Query("SELECT b FROM Book b WHERE SUBSTRING(b.releaseDate,0,4) not LIKE :year")
    Set<Book> findAllByReleaseDateNotInYear(String year);

    Set<Book> findAllByReleaseDateIsLessThan(LocalDate releaseDate);

    //select b Book b INNER JOIN author_id => ....where author.Last starting with
    Set<Book> findAllByAuthorLastNameStartingWith(String start);

    @Query("SELECT COUNT (b) FROM Book b WHERE LENGTH(b.title) > :length")
    int getNumberOfBooksWithTitleLength(int length);
}

