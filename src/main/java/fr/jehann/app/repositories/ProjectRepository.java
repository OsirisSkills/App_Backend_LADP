package fr.jehann.app.repositories;

import fr.jehann.app.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByProjectName(String name);


    @Query("FROM Project p JOIN FETCH p.projectMembers m WHERE m.id= :id")
    List<Project> findProjectsByProjectMembersId(@Param("id") long id);



    @Query("FROM Project p WHERE p.projectName= :n")
    Project findByName(@Param("n") String n);

    Page<Project> findAllByProjectNameContaining(String name, Pageable pageable);
}
