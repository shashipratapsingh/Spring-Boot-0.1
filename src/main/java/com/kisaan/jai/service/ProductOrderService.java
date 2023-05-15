package com.kisaan.jai.service;

import com.kisaan.jai.dto.ProductOrderDTO;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface ProductOrderService {

	ProductOrderDTO addProductOrder(ProductOrderDTO productOrderDto);
	
	List<ProductOrderDTO> getProductOrders(Long farmerId);
	
	ProductOrderDTO getProductOrder(Long farmerId, Long orderId);
	
	void deleteProductOrder(Long farmerId, Long orderId);
	
	ProductOrderDTO updateProductOrder(Long farmerId, Long orderId, ProductOrderDTO productOrderDto);
	
	
	List<ProductOrderDTO> getAllOrderByFarmerId(Long farmerId);

	List<ProductOrderDTO> getAllOrderByFarmerIdAndDateRange(Long farmerId, ZonedDateTime startDate, ZonedDateTime endDate);
}
