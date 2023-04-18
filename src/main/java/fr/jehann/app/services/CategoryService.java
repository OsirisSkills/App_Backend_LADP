package fr.jehann.app.services;

import fr.jehann.app.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

        List<CategoryDTO> findAll() throws Exception;

        CategoryDTO findById(long id) throws Exception;

        CategoryDTO save(CategoryDTO category) throws Exception;

        Boolean deleteById(long id);

        CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception;
}
