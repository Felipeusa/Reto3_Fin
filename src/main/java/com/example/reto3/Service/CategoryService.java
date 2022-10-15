package com.example.reto3.Service;

import com.example.reto3.Model.Category;
import com.example.reto3.Repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Crist
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return (List<Category>) categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);

    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> categoryEncontrado = categoryRepository.getCategory(category.getId());
            if (categoryEncontrado.isEmpty()) {
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    public Category update(Category category) {
        if (category.getId() != null) {
            Optional<Category> categoryEncontrado = categoryRepository.getCategory(category.getId());
            if (!categoryEncontrado.isEmpty()) {
                if (category.getDescription() != null) {
                    categoryEncontrado.get().setDescription(category.getDescription());
                }
                if (category.getName() != null) {
                    categoryEncontrado.get().setName(category.getName());
                }
                return categoryRepository.save(categoryEncontrado.get());
            }
        }
        return category;

    }

    public boolean deleteCategory(int categoryId) {
        Boolean resultado = getCategory(categoryId).map(categoryPorEliminar -> {
            categoryRepository.delete(categoryPorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

}

