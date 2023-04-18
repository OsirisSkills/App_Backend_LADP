package fr.jehann.app.repositories;

import fr.jehann.app.entities.ProjectFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {

    List<ProjectFile> findAllByFilename(String name);

    @Query("FROM ProjectFile c WHERE c.filename= :n")
    ProjectFile findByFilename(@Param("n") String n);

    Page<ProjectFile> findAllByFilenameContaining(String name, Pageable pageable);
}
