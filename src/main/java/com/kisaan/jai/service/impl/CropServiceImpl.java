package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.CategoryDTO;
import com.kisaan.jai.dto.CropDTO;
import com.kisaan.jai.dto.ProductDTO;
import com.kisaan.jai.dto.SubCategoryDTO;
import com.kisaan.jai.entity.Crop;
import com.kisaan.jai.entity.CropProductMapping;
import com.kisaan.jai.entity.Product;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.CropRepository;
import com.kisaan.jai.service.CropProductMappingService;
import com.kisaan.jai.service.CropService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CropServiceImpl implements CropService {

	@NotNull
	private final CropRepository cropRepository;

	@NotNull
	private final ModelMapper modelMapper;

	@NotNull
	private final CropProductMappingService cropProductMappingService;
	@NotNull
	private final PlotServiceImpl plotService;
	@NotNull
	private final CategoryServiceImpl categoryService;

	@NotNull
	private final SubCategoryServiceImpl subCategoryService;

	private ProductServiceImpl productService;

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}

	@PostConstruct
	private void init() {
		plotService.setCropService(this);
	}

	@Override
	public CropDTO save(CropDTO cropDTO) {
		Crop tempCrop = modelMapper.map(cropDTO, Crop.class);
		tempCrop.setCreatedBy("Admin"); // need to change once authentication added.
		tempCrop.setModifiedBy("Admin"); // need to change once authentication added.
		Crop crop = cropRepository.save(tempCrop);
		if(!CollectionUtils.isEmpty(cropDTO.getProducts())) {
			List<Product> productList = cropDTO.getProducts().stream().
					map(p -> {
						Product product = modelMapper.map(p, Product.class);
						product.setProductId(p.getId());
						return product;
					})
					.map(p -> addCropProductMapping(crop, p))
					.collect(Collectors.toList());
		}
		return getCrop(crop);
	}

	@Override
	public CropDTO findById(Long cropId) {
		Crop crop = getCropById(cropId);
		return modelMapper.map(crop, CropDTO.class);
//		return getCrop(crop);
	}

	@Override
	public CropDTO findByIdAndProductCategoryDetails(Long cropId) {
		Crop crop = getCropById(cropId);
		List<CategoryDTO> categoryList = crop.getProduct().stream()
				.map(p -> p.getProduct().getProductSubCategory().getProductCategory().getProductCategoryId())
				.map(categoryService::findById)
				.distinct()
				.collect(Collectors.toList());

		crop.setProduct(null);
		crop.setPlot(null);
		crop.setCropExpertMappings(null);
		log.info("farmer data is "+crop);
		CropDTO cropDTO = getCrop(crop);
		cropDTO.setCategories(categoryList);
		return cropDTO;
	}

	@Override
	public CropDTO findByIdAndProductDetails(Long cropId, Long categoryId) {
		Crop crop = getCropById(cropId);
		List<SubCategoryDTO> subCategoryList = crop.getProduct().stream()
				.filter(p -> categoryId.compareTo(p.getProduct().getProductSubCategory().getProductCategory().getProductCategoryId()) == 0)
				.map(p -> p.getProduct().getProductSubCategory().getProductSubCategoryId())
				.map(subCategoryService::getSubCategory)
				.map(productSubCategory -> modelMapper.map(productSubCategory, SubCategoryDTO.class))
				.distinct()
				.collect(Collectors.toList());

		crop.setProduct(null);
		crop.setPlot(null);
		crop.setCropExpertMappings(null);
		log.info("farmer data is "+crop);
		CropDTO cropDTO = getCrop(crop);
		cropDTO.setSubCategories(subCategoryList);
		return cropDTO;
	}

	@Override
	public CropDTO findByIdAndProductDetails(Long cropId, Long categoryId, Long subCategoryId) {
		Crop crop = getCropById(cropId);
		List<ProductDTO> productList = crop.getProduct().stream()
				.filter(p -> categoryId.compareTo(p.getProduct().getProductSubCategory().getProductCategory().getProductCategoryId()) == 0)
				.filter(p -> subCategoryId.compareTo(p.getProduct().getProductSubCategory().getProductSubCategoryId()) == 0)
				.map(p -> p.getProduct())
				.map(product -> modelMapper.map(product, ProductDTO.class))
				.distinct()
				.collect(Collectors.toList());

		crop.setProduct(null);
		crop.setPlot(null);
		crop.setCropExpertMappings(null);
		log.info("farmer data is "+crop);
		CropDTO cropDTO = getCrop(crop);
		cropDTO.setProducts(productList);
		return cropDTO;
	}

	public CropDTO findByIdWithoutPlotDetails(Long cropId, boolean isPlotDetailRequired) {
		Crop crop = getCropById(cropId);
		if(!isPlotDetailRequired) {
			crop.setPlot(null);
		}

		log.info("farmer data is "+crop);
		return getCrop(crop);
	}

	public CropDTO findByIdWithoutProductDetails(Long cropId, boolean isProductRequired) {
		Crop crop = getCropById(cropId);
		if(!isProductRequired) {
			crop.setProduct(null);
		}
		log.info("farmer data is "+crop);

		return getCrop(crop);
	}

	@Override
	public List<CropDTO> findAll() {
		List<Crop> cropList = cropRepository.findAllByIsActive(true);

		return cropList.stream().map(this::getCrop).collect(Collectors.toList());
	}

	@Override
	public List<CropDTO> search(String cropName) {
		if(cropName.isEmpty()) {
			throw new NoSuchElementExistException(" crop are not exists with blank crop name ");
		}
		List<Crop> crops = this.cropRepository.findByNameContainingAndIsActive(cropName, true);
		if (crops.isEmpty()) {
			throw new NoSuchElementExistException("Crops are not exists with this name "+cropName);
		}
		List<CropDTO> cropDto= crops.stream()
				.map(this::getCrop)
				.collect(Collectors.toList());

		 return cropDto;
	}

	@Override
	public CropDTO update(Long cropId, CropDTO cropDTO) {
		Crop tempCrop = modelMapper.map(cropDTO, Crop.class);
		tempCrop.setModifiedBy("Admin"); // need to change once authentication added.
		getCropById(cropId);
		tempCrop.setCropId(cropId);
		Crop updatedCrop = cropRepository.save(tempCrop);

		if(!CollectionUtils.isEmpty(cropDTO.getProducts())) {
			List<Product> productList = cropDTO.getProducts().stream().
					map(p -> {
						Product product = modelMapper.map(p, Product.class);
						product.setProductId(p.getId());
						return product;
					})
					.map(p -> addCropProductMapping(updatedCrop, p))
					.collect(Collectors.toList());
		}
		return getCrop(updatedCrop);
	}

	private Product addCropProductMapping(Crop crop, Product product) {
		CropProductMapping cropProductMapping = new CropProductMapping();
		cropProductMapping.setCrop(crop);
		cropProductMapping.setProduct(product);

		cropProductMappingService.save(cropProductMapping);
		return product;
	}

	@Override
	public void delete(Long cropId) {
		Crop crop = cropRepository.findById(cropId).orElseThrow(() -> new NoSuchElementExistException(new StringBuilder("crop is not exist with this id : ").append(cropId).toString()));
		crop.setIsActive(Boolean.FALSE);
		cropRepository.save(crop);
	}

	protected Crop getCropById(Long cropId) {
		return cropRepository.findByCropIdAndIsActive(cropId, true).orElseThrow(() -> new NoSuchElementExistException(
				new StringBuilder("Crop not found for id: ").append(cropId).toString()));
	}

	private CropDTO getCrop(Crop crop) {
		CropDTO cropDTO = modelMapper.map(crop, CropDTO.class);
		if (CollectionUtils.isEmpty(crop.getProduct())) {
			return cropDTO;
		}

		List<ProductDTO> productList = crop.getProduct().stream()
				.map(pcm -> productService.findById(pcm.getProduct().getProductId(), false)).collect(Collectors.toList());
		cropDTO.setProducts(productList);
		return cropDTO;
	}
}
