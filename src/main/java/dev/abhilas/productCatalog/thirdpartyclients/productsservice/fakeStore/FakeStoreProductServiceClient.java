package dev.abhilas.productCatalog.thirdpartyclients.productsservice.fakeStore;

import dev.abhilas.productCatalog.dto.FakeStoreProductDto;
import dev.abhilas.productCatalog.dto.GenericProductDto;
import dev.abhilas.productCatalog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakeStore.api.url}")
    private String fakeStoreApiUrl;
    @Value("${fakeStore.api.paths.product}")
    private String fakeStoreProductsApiPath;

    private String getProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
    private String createProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath;
    private String getProductsRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath;
    private String updateProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
    private String deleteProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakeStore.api.url}") String fakeStoreApiUrl, @Value("${fakeStore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplateBuilder=restTemplateBuilder;
        this.getProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
        this.createProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath;
        this.getProductsRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath;
        this.updateProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
        this.deleteProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto==null) {
            throw new NotFoundException("Product with id: " + id + "doesn't exist.");
        }

        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(getProductsRequestUrl, FakeStoreProductDto[].class);
        List<FakeStoreProductDto> answer = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto:response.getBody()) {
            answer.add(fakeStoreProductDto);
        }
        return answer;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(createProductRequestUrl,product, FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id, FakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(updateProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return fakeStoreProductDto;
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(deleteProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return fakeStoreProductDto;
    }
}
