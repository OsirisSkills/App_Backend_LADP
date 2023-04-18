package fr.jehann.app.controllers;


import fr.jehann.app.dto.ProjectDTO;
import fr.jehann.app.entities.Project;
import fr.jehann.app.services.ProjectService;
import fr.jehann.app.tools.DtoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> findAll() {
        try{
            return ResponseEntity.ok(projectService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable("id") Long id) throws Exception {
        ProjectDTO project = projectService.findById(id);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println(project.toString());
        return ResponseEntity.ok(DtoTools.convert(project, ProjectDTO.class));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDto) throws Exception {
        ProjectDTO project = projectService.create(projectDto);
        if (project == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(project);}
}
