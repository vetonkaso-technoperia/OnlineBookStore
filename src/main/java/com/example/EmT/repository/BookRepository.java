package com.example.EmT.repository;

import com.example.EmT.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findAll();
//    List<Book> findAllSortedByPrice(boolean asc);
//    List<Book> findAllByAuthorId(Long authorId);
//    Optional<Book> findById(Long id);
//    Book save(Book book);
//    void deleteById(Long id);

//    @Query(value = "select * from books p order by p.price asc;", nativeQuery = true)
//    @Query("select p from Book p order by p.price asc")
    List<Book> findAllByOrderByPriceAsc();



    List<Book> findAllByOrderByPriceDesc();
    List<Book> findAllByCategoryId(Long categoryId);


    //    @Query("select count(p) from Book p where p.price > :price")
    long countAllByPriceGreaterThan(@Param("price") Float price);

    List<Book> findAllByPriceGreaterThan(@Param("price") Float price);


    List<Book> findAllSortedByPrice(boolean asc);

    //    @Query(value = "select * from books join authors m where m.id = :id ;", nativeQuery = true)
//    @Query("select p from Book p where p.author.id = :id")
    List<Book> findAllByAuthorId(@Param("id") Long id);
    List<Book> findAllByNameLikeAndAuthorIdOrderByPriceDesc(String name, Long authorId);
}
