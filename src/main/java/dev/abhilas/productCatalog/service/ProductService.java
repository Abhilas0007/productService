package dev.abhilas.productCatalog.service;

import dev.abhilas.productCatalog.dto.FakeStoreProductDto;
import dev.abhilas.productCatalog.dto.GenericProductDto;
import dev.abhilas.productCatalog.exceptions.NotFoundException;
import dev.abhilas.productCatalog.model.Product;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto updateProductById(Long id, FakeStoreProductDto product);
    GenericProductDto deleteProductById(Long id);

    GenericProductDto createProduct(GenericProductDto product);
}
