package com.spring03.demo.repositories;

import com.spring03.demo.model.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    @Select("Select * FROM tb_category")
    List<Category> getAll();
}
