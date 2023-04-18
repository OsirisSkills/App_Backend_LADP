package fr.jehann.app.controllers;

import fr.jehann.app.dto.ProjectFileDTO;
import fr.jehann.app.services.ProjectFileService;
import fr.jehann.app.tools.DtoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projectFile")
public class ProjectFileController extends BaseController {

    @Autowired
    private ProjectFileService projectFileService;

    @GetMapping()
    public ResponseEntity<List<ProjectFileDTO>> findAll() {
        try {
            return ResponseEntity.ok(projectFileService.findAll());
        } catch (Exception e) {
            System.out.println("-------");
            System.out.println(e.getMessage());
            System.out.println("-------");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectFileDTO> findById(@PathVariable("id") Long id) throws Exception {
        ProjectFileDTO projectFileDTO = projectFileService.findById(id);
        if (projectFileDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(DtoTools.convert(projectFileDTO, ProjectFileDTO.class));
    }

    @PostMapping()
    public ResponseEntity<ProjectFileDTO> create(@RequestBody ProjectFileDTO projectFileDTO) {
        try {
            ProjectFileDTO savedProjectFile = projectFileService.save(projectFileDTO);
            return ResponseEntity.ok(savedProjectFile);
        } catch (Exception e) {
            System.out.println("-------");
            System.out.println(e.getMessage());
            System.out.println("-------");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectFileDTO> update(@PathVariable("id") Long id, @RequestBody ProjectFileDTO projectFileDTO) {
        try {
            ProjectFileDTO updatedProjectFile = projectFileService.update(id, projectFileDTO);
            return ResponseEntity.ok(updatedProjectFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        try {
            projectFileService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
