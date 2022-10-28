package com.example.clothesshop.controller.web;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "categoryApiOfWeb")
@CrossOrigin
@RequestMapping(path = "api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("parent_category")
    public List<CategoryDTO> getAllParentCategory() {
        return categoryService.findParentCategory();
    }

    @GetMapping("child_category")
    public List<CategoryDTO> getAllChildCategory() {
        return categoryService.findChildCategory();
    }

    @GetMapping("{parent_id}")
    public List<CategoryDTO> getCategoryByParentId(@PathVariable("parent_id") long id) {
        return categoryService.findByParentId(id);
    }

    @GetMapping("/detail/{id}")
    public CategoryDTO getCategoryById(@PathVariable("id") long id) {
        return categoryService.findById(id);
    }
}
