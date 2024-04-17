package com.example.miniblogback.controller;

import com.example.miniblogback.config.dto.ResponseDto;
import com.example.miniblogback.config.dto.ResponseUtil;
import com.example.miniblogback.service.CategoryService;
import com.example.miniblogback.vo.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseDto<List<CategoryDto.CategoryResponse>> categoryList(){
        List<CategoryDto.CategoryResponse>list = categoryService.categoryResponseList();
        return ResponseUtil.ok(HttpStatus.OK.value(),list);
    }

    @PostMapping("/create")
    public ResponseDto<?>categoryCreate(@RequestBody CategoryDto.CategoryRequest request){
        categoryService.categoryCreate(request);
        return ResponseUtil.ok(HttpStatus.OK.value(),"category Create");
    }

    @DeleteMapping("/{id}")
    public ResponseDto<?>categoryDelete(@PathVariable("id")Long categoryId){
        categoryService.categoryDelete(categoryId);
        return ResponseUtil.ok(HttpStatus.NO_CONTENT.value(), "category Delete!");
    }
}
