package dev.abhilas.productCatalog.controllers;

import dev.abhilas.productCatalog.dto.GenericProductDto;
import dev.abhilas.productCatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    //Recommended way to inject dependency- constructor injection
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService=productService;
    }
    //Way2 - Field injection
//    @Autowired
//    private ProductService productService;

    //Way3-Using setter
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping
    public void getAllProducts() {

    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProductById() {

    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public void updateProductById() {

    }
}
