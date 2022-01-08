package com.ntnt.highblog.dmm.service;

import com.ntnt.highblog.dmm.model.entity.Category;
import com.ntnt.highblog.dmm.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Category> fetchAllCategories() {
        log.info("Fetch all categories");
        return repository.findAll();
    }
}
