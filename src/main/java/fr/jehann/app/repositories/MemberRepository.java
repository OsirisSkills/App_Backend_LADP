package fr.jehann.app.repositories;

import fr.jehann.app.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByLastName(String firstName);

    Page<Member> findAllByFirstNameContaining(String firstName, Pageable pageable);

    /**
     * Recherche d'un membre en fonction de son email
     * @param email
     * @return <p>Retourne un membre </p>
     */

   //@Query("From Member m WHERE m.email= : email")
    Optional<Member> findByEmail(@Param("email") String email );
}
