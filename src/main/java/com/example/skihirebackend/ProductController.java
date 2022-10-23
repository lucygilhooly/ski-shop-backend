package com.example.skihirebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    ProductService productService;

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }


    // CREATE

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // READ

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World!");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getProducts(@RequestParam(required = false) String type, @RequestParam(defaultValue = "10") int limit) {

        if (type != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByType(type, limit));
        }

        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts(limit));

    }

    @GetMapping("/products/ids")
    public ResponseEntity<List<Long>> getProductIds() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductIds());
    }

    @GetMapping("/products/types")
    public ResponseEntity<List<String>> getProductTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getTypes());
    }

    @GetMapping("/product/random")
    public ResponseEntity<Product> getRandomProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getRandomProduct());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    // UPDATE

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product newProduct, @PathVariable long id) {
        newProduct.setId(id);
        productService.updateProduct(newProduct, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(newProduct);
    }

    // DELETE

    @DeleteMapping("/product/{id}")
    @Transactional
    public ResponseEntity<String> deleteProductById(@PathVariable long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
