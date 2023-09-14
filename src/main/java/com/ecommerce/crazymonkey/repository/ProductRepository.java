package com.ecommerce.crazymonkey.repository;

import com.ecommerce.crazymonkey.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

}
