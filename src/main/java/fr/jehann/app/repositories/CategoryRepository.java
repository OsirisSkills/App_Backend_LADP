package fr.jehann.app.repositories;

import fr.jehann.app.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByName(String name);

    @Query("FROM Category c WHERE c.name= :n")
    Category findByName(@Param("n") String n);

    Page<Category> findAllByNameContaining(String name, Pageable pageable);
}
