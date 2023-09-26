package dev.abhilas.productCatalog.service;

import dev.abhilas.productCatalog.dto.FakeStoreProductDto;
import dev.abhilas.productCatalog.dto.GenericProductDto;
import dev.abhilas.productCatalog.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getProductsRequestUrl = "https://fakestoreapi.com/products";
    private String updateProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String deleteProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder=restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        return product;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto==null) {
            throw new NotFoundException("Product with id: " + id + "doesn't exist.");
        }

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(getProductsRequestUrl, FakeStoreProductDto[].class);
        List<GenericProductDto> answer = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto:response.getBody()) {
            answer.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
        return answer;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestUrl,product, GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public GenericProductDto updateProductById(Long id, FakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>>responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(updateProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(deleteProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }
}
