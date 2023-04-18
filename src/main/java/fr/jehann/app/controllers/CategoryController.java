package fr.jehann.app.controllers;

import fr.jehann.app.dto.CategoryDTO;
import fr.jehann.app.entities.Category;
import fr.jehann.app.services.CategoryService;
import fr.jehann.app.tools.DtoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        try {
            return ResponseEntity.ok(categoryService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) throws Exception {
        CategoryDTO categoryDTO = categoryService.findById(id);
        if (categoryDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(DtoTools.convert(categoryDTO, CategoryDTO.class));
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryDTO savedMember = categoryService.save(categoryDTO);
            return ResponseEntity.ok(savedMember);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory (@PathVariable long id, @RequestBody CategoryDTO updatedCategoryDTO)
    {
        try {
        CategoryDTO existingCategory = categoryService.findById(id);
        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        } else {
            existingCategory.setName(updatedCategoryDTO.getName());
            existingCategory.setDescription(updatedCategoryDTO.getDescription());
            CategoryDTO updatedCategory = categoryService.save(existingCategory);
            return ResponseEntity.ok(updatedCategory);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") long id) {
            try {
                categoryService.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
    }
