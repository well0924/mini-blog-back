package com.example.miniblogback.service;

import com.example.miniblogback.config.exception.CustomExceptionHandler;
import com.example.miniblogback.config.exception.Enum.CategoryErrorCode;
import com.example.miniblogback.repository.CategoryMapper;
import com.example.miniblogback.vo.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public List<CategoryDto.CategoryResponse>categoryResponseList(){
        List<CategoryDto.CategoryResponse>categoryResponseList = categoryMapper.categoryList();

        if(categoryResponseList.isEmpty()){
            throw new CustomExceptionHandler(CategoryErrorCode.NOT_CATEGORY);
        }
        return categoryResponseList;
    }

    @Transactional
    public void categoryCreate(CategoryDto.CategoryRequest request){
        categoryMapper.categoryCreate(request);
    }

    @Transactional
    public void categoryDelete(Long categoryId){
        categoryMapper.categoryDelete(categoryId);
    }

}
