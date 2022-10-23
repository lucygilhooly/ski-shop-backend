package com.example.skihirebackend;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product has not been found");
    }
}

