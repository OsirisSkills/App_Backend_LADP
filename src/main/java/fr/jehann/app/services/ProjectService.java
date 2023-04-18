package fr.jehann.app.services;

import fr.jehann.app.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> findAll() throws Exception;

    ProjectDTO findById(Long id);

    ProjectDTO create(ProjectDTO projectDto) throws Exception;
}
