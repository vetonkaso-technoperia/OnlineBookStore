package com.example.EmT.repository;

import com.example.EmT.model.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//ovoj Bean iako e interface, bidejkji nasleduva od JpaRepository,
// kje se kreira samo dokolku aktiveniot profil NE e in-memory (ova vazhi i nieden profil da ne e aktiven)
//i vo toj sluchaj
//sekade kade shto se koristi AuthorRepository interfejsot (kako vo servisite)
//implementacijata koja kje ja prepoznae kje bide ovaa (defaultnata od Jpa)
//namesto ManaufacturerRepositoryInMemoryImpl
@Repository
@Profile("!in-memory")
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    List<Author> findAll();
//    List<Author> findAllByName(String name);
//    Optional<Author> findById(Long id);
//    Author save(Author author);
//    void deleteById(Long id);

}
