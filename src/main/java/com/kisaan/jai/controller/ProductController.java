package com.kisaan.jai.controller;

import com.kisaan.jai.dto.ProductIdDTO;
import com.kisaan.jai.dto.ProductDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/catalogue/product")
@Slf4j
public class ProductController {

	@Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductDTO productDto){
    	productDto.setSubCategoryId(productDto.getSubCategoryId());
        productDto.setCategoryId(productDto.getCategoryId());
        log.info("inside the controller add product method");
        ProductDTO createProduct = this.productService.save(productDto);
        createProduct.setCategoryId(productDto.getCategoryId());
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().message("Saved Successfully.").status(true).productId(createProduct.getId().toString()).httpStatus(HttpStatus.CREATED).build());
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProduct(@Valid @RequestBody ProductIdDTO categoryIdDTO){
        List<ProductDTO> products = productService.getProductsByCategoryIdAndSubCategoryId(categoryIdDTO.getCategoryId(), categoryIdDTO.getSubCategoryId());
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().responseBody(products).message("Data fetched").count(products.size()).httpStatus(HttpStatus.OK).build());
    }

    @GetMapping
    public ResponseEntity<Object> getProductById(@Valid @RequestBody ProductIdDTO categoryIdDTO){
    	ProductDTO product = productService.getProductByCategoryIdAndSubCategoryIdAndProductId(categoryIdDTO.getCategoryId(), categoryIdDTO.getSubCategoryId(), categoryIdDTO.getProductId());
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().responseBody(product).message("Data found").status(true).httpStatus(HttpStatus.OK).build());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteProduct( @RequestBody ProductIdDTO categoryIdDTO){
    	ProductDTO product = productService.getProductByCategoryIdAndSubCategoryIdAndProductId(categoryIdDTO.getCategoryId(), categoryIdDTO.getSubCategoryId(), categoryIdDTO.getProductId());
        productService.delete(categoryIdDTO.getProductId());
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().status(true).message("Product deleted successfully").httpStatus(HttpStatus.OK).build());
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody ProductDTO productDto){
        productDto.setSubCategoryId(productDto.getSubCategoryId());
        productDto.setCategoryId(productDto.getCategoryId());
    	//ProductDTO product = productService.getProductByCategoryIdAndSubCategoryIdAndProductId(productDto.getCategoryId(), productDto.getSubCategoryId(), productId);
        ProductDTO createProduct = this.productService.update(productDto.getId(), productDto);
        createProduct.setCategoryId(productDto.getCategoryId());
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().productId(createProduct.getId().toString()).message("Saved Successfully.").status(true).httpStatus(HttpStatus.OK).build());
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getProductsByName(@Valid @RequestBody ProductIdDTO productIdDTO){
        List<ProductDTO> createProduct = productService.getProductsByCategoryIdAndSubCategoryId(productIdDTO.getCategoryId(), productIdDTO.getSubCategoryId());
        List<ProductDTO> products = this.productService.search(productIdDTO.getName());
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().status(true).message("Data found").responseBody(products).count(products.size()).httpStatus(HttpStatus.OK).build());
    }
}
