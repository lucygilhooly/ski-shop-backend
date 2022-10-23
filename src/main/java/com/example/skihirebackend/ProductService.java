package com.example.skihirebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    // CREATE

    public void addProduct(Product product) {
        productRepository.save(product);
    }


    // READ

    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return product.get();
    }

    public Product getRandomProduct() {
        return productRepository.getRandomProduct();
    }

    public List<Long> getProductIds() {
        return productRepository.getDistinctIds();
    }

    public List<String> getTypes() {
        return productRepository.getDistinctType();
    }

    public List<Product> getProductByType(String type, int limit) {
        List<Product> products = productRepository.getAllByType(type);

        return products
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts(int limit) {
        return productRepository
                .findAll()
                .stream()
                .limit(limit)
                .collect(Collectors.toList());

    }

    // UPDATE

    public void updateProduct(Product newProduct, long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }

        newProduct.setId(id);

       productRepository.save(newProduct);
    }

    // DELETE
    @Transactional
    public void deleteProductById(long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }

        productRepository.deleteProductById(id);
    }
}
