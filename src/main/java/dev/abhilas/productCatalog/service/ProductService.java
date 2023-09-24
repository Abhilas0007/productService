package dev.abhilas.productCatalog.service;

import dev.abhilas.productCatalog.dto.GenericProductDto;
import dev.abhilas.productCatalog.model.Product;

public interface ProductService {
    GenericProductDto getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto product);
}
