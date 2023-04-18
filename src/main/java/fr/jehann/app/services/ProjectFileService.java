package fr.jehann.app.services;

import fr.jehann.app.dto.ProjectFileDTO;

import java.util.List;

public interface ProjectFileService {

    List<ProjectFileDTO> findAll() throws Exception;

    ProjectFileDTO findById(long id) throws Exception;

    ProjectFileDTO save(ProjectFileDTO projectFileDTO) throws Exception;

    ProjectFileDTO update(Long id, ProjectFileDTO projectFileDTO) throws Exception;

    boolean deleteById(long id) throws Exception;

}
