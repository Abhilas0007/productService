package dev.abhilas.productCatalog.service;

import dev.abhilas.productCatalog.dto.FakeStoreProductDto;
import dev.abhilas.productCatalog.dto.GenericProductDto;
import dev.abhilas.productCatalog.exceptions.NotFoundException;
import dev.abhilas.productCatalog.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id, FakeStoreProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }
}
