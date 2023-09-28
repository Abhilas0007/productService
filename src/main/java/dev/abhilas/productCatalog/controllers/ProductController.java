package dev.abhilas.productCatalog.controllers;

import dev.abhilas.productCatalog.dto.FakeStoreProductDto;
import dev.abhilas.productCatalog.dto.GenericProductDto;
import dev.abhilas.productCatalog.exceptions.NotFoundException;
import dev.abhilas.productCatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Qualifier is used to explicitly mark which Product Service to use to avoid ambiguity
// else we can use @Primary on that service to mark it as default in case of no Qualifier
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
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        //Changing Response codes
        return new ResponseEntity<>(
                productService.deleteProductById(id),
                HttpStatus.OK
        );
//        return productService.deleteProductById(id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody FakeStoreProductDto product) {
        return productService.updateProductById(id,product);
    }
}
