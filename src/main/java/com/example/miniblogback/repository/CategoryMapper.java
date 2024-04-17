package com.example.miniblogback.repository;

import com.example.miniblogback.vo.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto.CategoryResponse>categoryList();
    void categoryCreate(CategoryDto.CategoryRequest request);
    void categoryDelete(Long categoryId);
}
