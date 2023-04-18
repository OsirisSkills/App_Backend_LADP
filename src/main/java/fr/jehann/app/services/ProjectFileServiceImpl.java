package fr.jehann.app.services;

import fr.jehann.app.dto.ProjectFileDTO;
import fr.jehann.app.entities.ProjectFile;
import fr.jehann.app.repositories.ProjectFileRepository;
import fr.jehann.app.tools.DtoTools;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjectFileServiceImpl implements ProjectFileService {

    @Autowired
    private ProjectFileRepository projectFileRepository;

    @Override
    public List<ProjectFileDTO> findAll() throws Exception {
        List<ProjectFile> projectFiles = projectFileRepository.findAll();

        List<ProjectFileDTO> result = new ArrayList<>();
        for(ProjectFile files: projectFiles){
            result.add(DtoTools.convert(files, ProjectFileDTO.class));
        }
        return result;
    }

    @Override
    public ProjectFileDTO findById(long id) throws Exception {
        List<ProjectFile> projectFiles = projectFileRepository.findAll();

        for (ProjectFile files: projectFiles){
            if (files.getId() == id){
                return DtoTools.convert(files, ProjectFileDTO.class);
            }
        }
        return null;
    }

    @Override
    public ProjectFileDTO save(ProjectFileDTO projectFileDTO) throws Exception {
        ProjectFile projectFile = DtoTools.convert(projectFileDTO, ProjectFile.class);
        if (projectFile == null){
            throw new Exception("Conversion to ProjectFile object failed");
        }
        ProjectFile saveProjectFile = projectFileRepository.save(projectFile);
        return DtoTools.convert(saveProjectFile, ProjectFileDTO.class);
    }

    @Override
    public ProjectFileDTO update(Long id, ProjectFileDTO projectFileDTO) throws Exception {
        ProjectFile projectFile = DtoTools.convert(projectFileDTO, ProjectFile.class);
        if (projectFile == null){
            throw new Exception("conversionToFail");
        }
        projectFile.setId(id);
        ProjectFile saveProjectFile = projectFileRepository.save(projectFile);
        return DtoTools.convert(saveProjectFile, ProjectFileDTO.class);
    }

    @Override
    public boolean deleteById(long id) throws Exception {
        try{
            projectFileRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
