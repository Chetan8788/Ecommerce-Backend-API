package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.ProductDto;

@Repository
public interface ProductDtoDao extends JpaRepository<ProductDto, Integer> {

}
