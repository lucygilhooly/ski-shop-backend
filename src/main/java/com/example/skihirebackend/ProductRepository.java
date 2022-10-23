package com.example.skihirebackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // READ
    List<Product> getAllByType(String type);

    @Query(value="SELECT DISTINCT id FROM product ", nativeQuery = true)
    List<Long> getDistinctIds();

    @Query(value="SELECT * FROM product ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Product getRandomProduct();

    @Query(value="SELECT DISTINCT type FROM product ORDER BY type", nativeQuery = true)
    List<String> getDistinctType();


    // DELETE
    void deleteProductById(long id);
}
