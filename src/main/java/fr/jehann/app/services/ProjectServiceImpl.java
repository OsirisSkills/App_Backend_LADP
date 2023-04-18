package fr.jehann.app.services;

import fr.jehann.app.dto.ProjectDTO;
import fr.jehann.app.entities.Project;
import fr.jehann.app.repositories.CategoryRepository;
import fr.jehann.app.repositories.ProjectRepository;
import fr.jehann.app.tools.DtoTools;
import jakarta.transaction.Transactional;
import org.hibernate.query.sqm.sql.ConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProjectDTO> findAll() throws Exception {
        List<Project> projects = projectRepository.findAll();

        List<ProjectDTO> result = new ArrayList<>();
        for(Project proj :projects){
            System.out.println(proj.toString());
            result.add(DtoTools.convert(proj, ProjectDTO.class));
            System.out.println(result.toString());
            System.out.println("------------------------------------");
        }
        return result;
    }

    @Override
    public ProjectDTO findById(Long id) {
        Optional<Project> projects = projectRepository.findById(id);
        try {
            return DtoTools.convert(projects, ProjectDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectDTO create(ProjectDTO projectDto)  {

        Project project = null;
        try {
            project = DtoTools.convert(projectDto, Project.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (project == null){
            throw new ConversionException("conversionToFail");
        }
        project.setCategory(categoryRepository.findById(project.getCategory().getId()).orElse(null));
        Project saveProject = projectRepository.save(project);
        try {
            return DtoTools.convert(saveProject, ProjectDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
