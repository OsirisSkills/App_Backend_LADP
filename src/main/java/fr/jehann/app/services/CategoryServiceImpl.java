package fr.jehann.app.services;

import fr.jehann.app.dto.CategoryDTO;
import fr.jehann.app.entities.Category;
import fr.jehann.app.repositories.CategoryRepository;
import fr.jehann.app.tools.DtoTools;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() throws Exception {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> result = new ArrayList<>();
        for(Category cat :categories){
            result.add(DtoTools.convert(cat, CategoryDTO.class));
        }
        return result;
    }

    @Override
    public CategoryDTO findById(long id) throws Exception {
        List<Category> categories = categoryRepository.findAll();

        for(Category cat :categories) {
            if (cat.getId() == id) {
                return DtoTools.convert(cat, CategoryDTO.class);
            }
        }
        return null;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) throws Exception {
        Category category = DtoTools.convert(categoryDTO, Category.class);
        if (category == null) {
            throw new Exception("Conversion to Category object failed");
        }
        Category savedCategory = categoryRepository.save(category);
        return DtoTools.convert(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception {
        Category category = DtoTools.convert(categoryDTO, Category.class);
        if (category == null)
        {
           throw new Exception("Conversion to Category object failed");
        }
        Category savedCategory = categoryRepository.save(category);
        return DtoTools.convert(savedCategory, CategoryDTO.class);
    }

    @Override
    public Boolean deleteById(long id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
