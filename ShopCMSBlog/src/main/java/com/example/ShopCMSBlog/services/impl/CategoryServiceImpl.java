package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.dtos.Queries.CategoryQueryDto;
import com.example.ShopCMSBlog.entites.CategoryEntity;
import com.example.ShopCMSBlog.mappers.CategoryMapper;
import com.example.ShopCMSBlog.repositories.CategoryRepository;
import com.example.ShopCMSBlog.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CommonServiceImpl<CategoryEntity, Long, CategoryRepository> implements CategoryService {
    private final CategoryMapper categoryMapper;
    public CategoryServiceImpl(CategoryRepository repo, CategoryMapper categoryMapper) {
        super(repo);
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        CategoryEntity categoryEntity = repo.getCategoryById(id);
        return categoryMapper.toDto(categoryEntity);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> list = repo.findAll();
        return categoryMapper.toDtoList(list);
    }

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(category);
        CategoryEntity savedCategoryEntity = repo.saveAndFlush(categoryEntity);
        return categoryMapper.toDto(savedCategoryEntity);
    }

    @Override
    public CategoryDto deleteCategory(Long id) {
        CategoryEntity CategoryToDelete = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond Category with this ID: " + id + ". Cann't delete!."));

        repo.delete(CategoryToDelete);
        return categoryMapper.toDto(CategoryToDelete);
    }

    @Override
    public Page<CategoryDto> getCategory(CategoryQueryDto categoryQueryDto) {
        return repo.getCategory(categoryQueryDto);
    }

}
