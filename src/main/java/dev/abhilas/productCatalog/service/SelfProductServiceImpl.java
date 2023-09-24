package dev.abhilas.productCatalog.service;

import dev.abhilas.productCatalog.dto.GenericProductDto;
import dev.abhilas.productCatalog.model.Product;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }
}
