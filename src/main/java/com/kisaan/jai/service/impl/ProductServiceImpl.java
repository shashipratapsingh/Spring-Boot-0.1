package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.CropDTO;
import com.kisaan.jai.dto.ProductDTO;
import com.kisaan.jai.dto.ProductFeatureDTO;
import com.kisaan.jai.entity.*;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.ProductRepository;
import com.kisaan.jai.service.CropProductMappingService;
import com.kisaan.jai.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @NotNull
    private final ProductRepository productRepository;
    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final SubCategoryServiceImpl subCategoryService;

    @NotNull
    private final CropServiceImpl cropService;

    @NotNull
    private final CropProductMappingService cropProductMappingService;

    @NotNull
    private final ProductFeatureServiceImpl productFeatureService;

    @PostConstruct
    private void init() {
        this.cropService.setProductService(this);
    }

    protected Product getProductById(Long productId) {
        return productRepository.findByProductIdAndIsActive(productId, true)
                .orElseThrow(() -> new NoSuchElementExistException(
                        new StringBuilder("Product not found for id: ").append(productId).toString()));
    }

    @Override
    public ProductDTO findById(Long productId) {
        Product product = getProductById(productId);
        return getProduct(product);
    }

    public ProductDTO findById(Long productId, boolean isCropRequired) {
        Product product = getProductById(productId);
        if(!isCropRequired) {
            product.setCrop(null);
        }
        return getProduct(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAllByIsActive(true);
        List<ProductDTO> productDTOList=products.stream()
                .map(this::getProduct)
                .collect(Collectors.toList());
        return productDTOList;
    }

    @Transactional
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductSubCategory productSubCategory = subCategoryService.getProductSubCategory(productDTO.getCategoryId(), productDTO.getSubCategoryId());
    	log.info("inside the service in save method");
        Product tempProduct = modelMapper.map(productDTO, Product.class);
        tempProduct.setProductSubCategory(productSubCategory);
        tempProduct.setLongDesc(null);
        Product product = productRepository.save(tempProduct);

        if(!CollectionUtils.isEmpty(productDTO.getLongDesc())) {
            ProductDTO tempProductDTO = modelMapper.map(product, ProductDTO.class);

            productDTO.getLongDesc().stream()
                    .map(ld -> {
                        ld.setProduct(tempProductDTO);
                        return ld;
                    })
                    .map(ld -> modelMapper.map(ld, ProductFeatureDTO.class))
                    .map(productFeatureService::addProductFeature).collect(Collectors.toList());
        }

        /*List<Crop> productList = productDTO.getCrops().stream().
                map(crop -> {
                    Crop tempCrop = modelMapper.map(crop, Crop.class);
                    tempCrop.setCropId(crop.getId());

                    return tempCrop;
                })
                .map(crop -> addCropProductMapping(crop, product))
                .collect(Collectors.toList());*/
        return getProduct(product);
    }

    @Transactional
    @Override
    public ProductDTO update(Long integer, ProductDTO productDTO) {
        ProductSubCategory productSubCategory = subCategoryService.getProductSubCategory(productDTO.getCategoryId(), productDTO.getSubCategoryId());

        Product tempProduct = modelMapper.map(productDTO, Product.class);
        tempProduct.setProductId(integer);
        tempProduct.setLongDesc(null);
        Product product= productRepository.save(tempProduct);

        if(!CollectionUtils.isEmpty(productDTO.getLongDesc())) {
            ProductDTO tempProductDTO = modelMapper.map(product, ProductDTO.class);

            productFeatureService.getProductFeatures(product.getProductId()).stream()
                    .map(ProductFeatureDTO::getId)
                    .map(pfId -> {
                        productFeatureService.deleteProductFeature(product.getProductId(), pfId);
                        return pfId;
                    }).collect(Collectors.toList());

            productDTO.getLongDesc().stream()
                    .map(ld -> {
                        ld.setProduct(tempProductDTO);
                        return ld;
                    })
                    .map(ld -> modelMapper.map(ld, ProductFeatureDTO.class))
                    .map(productFeatureService::addProductFeature).collect(Collectors.toList());
        }

        if(!CollectionUtils.isEmpty(productDTO.getCrops())) {
            List<Crop> productList = productDTO.getCrops().stream().
                    map(crop -> {
                        Crop tempCrop = modelMapper.map(crop, Crop.class);
                        tempCrop.setCropId(crop.getId());

                        return tempCrop;
                    })
                    .map(crop -> addCropProductMapping(crop, product))
                    .collect(Collectors.toList());
        }
        return getProduct(product);
    }

    @Override
    public void delete(Long productId) {
        Product product = productRepository.findByProductIdAndIsActive(productId, true).orElseThrow(() -> new NoSuchElementExistException("product is not exist with this product id "+productId));
        product.setIsActive(Boolean.FALSE);
        product.setOrderProducts(null);
        productRepository.save(product);
    }

    @Override
    public List<ProductDTO> search(String value) {
        List<Product> products = productRepository.findByNameContainingAndIsActive(value, true);

        List<ProductDTO> productDto=products.stream()
                .map(this::getProduct)
                .collect(Collectors.toList());
        if (productDto.isEmpty()){
            throw new NoSuchElementExistException("Product with this name is not exists "+value);
        }
        return productDto;
    }

    @Override
    public List<ProductDTO> getProductsByCategoryIdAndSubCategoryId(Long categoryId, Long subCategoryId) {
        List<Product> productList = productRepository.findByIsActiveAndProductSubCategory_ProductSubCategoryIdAndProductSubCategory_ProductCategory_ProductCategoryId(true, subCategoryId, categoryId);
        if (productList.isEmpty()) {
			throw new NoSuchElementExistException(
                    new StringBuilder("sub category id ").append(subCategoryId).append(" is not exist category id ").append(categoryId).toString());
		}
        List<ProductDTO> productDTOList = productList.stream()
                .map(this::getProduct)
                .collect(Collectors.toList());
        return productDTOList;
    }

    private ProductDTO getProduct(Product product) {
        if(!CollectionUtils.isEmpty(product.getLongDesc())) {
            List<ProductFeature> productFeatures = product.getLongDesc().stream()
                    .map(ld -> {
                        ld.setProduct(null);
                        return ld;
                    }).collect(Collectors.toList());
            product.setLongDesc(productFeatures);
        }
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setSubCategoryId(product.getProductSubCategory().getProductSubCategoryId());
        productDTO.setCategoryId(product.getProductSubCategory().getProductCategory().getProductCategoryId());

        if (CollectionUtils.isEmpty(product.getCrop())) {
            return productDTO;
        }

        List<CropProductMapping> mappingList = product.getCrop().stream().collect(Collectors.toList());
        product.setCrop(null);

        List<CropDTO> cropList = mappingList.stream()
                .map(pcm -> cropService.findByIdWithoutProductDetails(pcm.getCrop().getCropId(), false)).collect(Collectors.toList());
        productDTO.setCrops(cropList);
        productDTO.setSubCategoryId(product.getProductSubCategory().getProductSubCategoryId());

        return productDTO;
    }

    @Override
    public ProductDTO getProductByCategoryIdAndSubCategoryIdAndProductId(Long categoryId, Long subCategoryId, Long productId) {
    	Product product = productRepository.findByProductIdAndIsActiveAndProductSubCategory_ProductSubCategoryIdAndProductSubCategory_ProductCategory_ProductCategoryId(productId, true, subCategoryId, categoryId)
    			.orElseThrow(() -> new NoSuchElementExistException(" product does not exists under this category and sub category "));

        return getProduct(product);
    }

    private Crop addCropProductMapping(Crop crop, Product product) {
        CropProductMapping cropProductMapping = new CropProductMapping();
        cropProductMapping.setCrop(crop);
        cropProductMapping.setProduct(product);

        cropProductMappingService.save(cropProductMapping);
        return crop;
    }
}