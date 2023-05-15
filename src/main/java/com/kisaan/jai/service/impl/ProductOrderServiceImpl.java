package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.OrderProductDTO;
import com.kisaan.jai.dto.ProductDTO;
import com.kisaan.jai.dto.ProductOrderDTO;
import com.kisaan.jai.entity.OrderProduct;
import com.kisaan.jai.entity.Product;
import com.kisaan.jai.entity.ProductOrder;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.ProductOrderRepository;
import com.kisaan.jai.service.OrderProductService;
import com.kisaan.jai.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductOrderServiceImpl implements ProductOrderService {

	@NotNull
	private final ProductOrderRepository productOrderRepository;
	@NotNull
	private final FarmerServiceImpl farmerService;

	@NotNull
	private final ModelMapper modelMapper;

	@NotNull
	private final OrderProductService orderProductService;

	@NotNull
	private final ProductServiceImpl productService;

	@Override
	public ProductOrderDTO addProductOrder(ProductOrderDTO productOrderDto) {
		log.info("inside the service layer in add product order method");
		farmerService.findById(productOrderDto.getFarmerId());

		Double orderAmount = Math.round(getTotalAmount(productOrderDto.getOrderProducts()) * 100.0) / 100.0;
		ProductOrder tempProductOrder = modelMapper.map(productOrderDto, ProductOrder.class);
		tempProductOrder.setOrderProducts(null);
		tempProductOrder.setOrderAmount(orderAmount);
		ProductOrder productOrder = productOrderRepository.save(tempProductOrder);

		updateOrderProductMapping(productOrder, productOrderDto.getOrderProducts());
		return getProductOrderDetail(productOrder);
	}

	@Override
	public List<ProductOrderDTO> getProductOrders(Long farmerId) {
		log.info("inside the service layer in get all product order method");
		farmerService.findById(farmerId);
		List<ProductOrder> listProductOrders = this.productOrderRepository.findByFarmerId(farmerId);
		if(listProductOrders.isEmpty()) {
			throw new NoSuchElementExistException("Product orders is not exists with this farmer ");
		}
		List<ProductOrderDTO> productDto = listProductOrders.stream()
				.map(this::getProductOrderDetail)
				.collect(Collectors.toList());
		return productDto;
	}

	@Override
	public ProductOrderDTO getProductOrder(Long farmerId, Long orderId) {
		log.info("inside the service layer in get product order method");
		farmerService.findById(farmerId);
		ProductOrder productOrder = productOrderRepository.findProductOrderByFarmerIdAndId(farmerId, orderId)
				.orElseThrow(() -> new NoSuchElementExistException("order is not exists with this order id "+orderId+ " for this farmer "));

		return getProductOrderDetail(productOrder);
	}

	@Override
	public void deleteProductOrder(Long farmerId, Long orderId) {
		log.info("inside the service layer in delette product order method");
		farmerService.findById(farmerId);
		ProductOrder productOrder = this.productOrderRepository.findById(orderId)
				.orElseThrow(() -> new NoSuchElementExistException("Product order doesn't exist for id :" + orderId));
		productOrderRepository.delete(productOrder);
	}

	@Override
	public ProductOrderDTO updateProductOrder(Long farmerId, Long orderId, ProductOrderDTO productOrderDto) {
		log.info("inside the service layer in update product order method");
		farmerService.findById(farmerId);
		productOrderRepository.findById(orderId)
				.orElseThrow(() -> new NoSuchElementExistException("Product order is not exist with this farmer "+farmerId));
		ProductOrder tempProductOrder = modelMapper.map(productOrderDto, ProductOrder.class);
		tempProductOrder.setFarmerId(farmerId);
		tempProductOrder.setId(orderId);
		ProductOrder productOrder = productOrderRepository.save(tempProductOrder);

		updateOrderProductMapping(productOrder, productOrderDto.getOrderProducts());
		return getProductOrderDetail(productOrder);
	}

	@Override
	public List<ProductOrderDTO> getAllOrderByFarmerId(Long farmerId){
		List<ProductOrder> productOrderList = productOrderRepository.findByFarmerId(farmerId);
		if(productOrderList.isEmpty()) {
			throw new NoSuchElementExistException("product order is not exist with this farmer");
		}
		List<ProductOrderDTO> orders = productOrderList.stream()
			.map(this::getProductOrderDetail).collect(Collectors.toList());
	return orders;
	}

	@Override
	public List<ProductOrderDTO> getAllOrderByFarmerIdAndDateRange(Long farmerId, ZonedDateTime startDate, ZonedDateTime endDate){
		List<ProductOrder> productOrderList = productOrderRepository.findByFarmerIdAndCreatedDateBetween(farmerId, startDate, endDate);

		if(productOrderList.isEmpty()) {
			throw new NoSuchElementExistException("product order is not exist with this farmer");
		}
		List<ProductOrderDTO> orders = productOrderList.stream()
				.map(this::getProductOrderDetail).collect(Collectors.toList());
		return orders;
	}

	private List<OrderProduct> updateOrderProductMapping(ProductOrder productOrder, List<OrderProductDTO> orderProducts) {
		return orderProducts.stream()
				.map(orderProduct -> {
					Product product = productService.getProductById(orderProduct.getProduct().getId());
					return OrderProduct.builder()
							.product(product)
							.productOrder(productOrder)
							.quantity(orderProduct.getQuantity())
							.price(Math.round(getProductTotal(orderProduct) * 100.0) / 100.0).build();
				}).map(orderProductService::save).collect(Collectors.toList());
	}

	private ProductOrderDTO getProductOrderDetail(ProductOrder productOrder) {

		ProductOrderDTO productOrderDTO = new ProductOrderDTO();
		productOrderDTO.setId(productOrder.getId());
		productOrderDTO.setOrderAmount(Math.round(productOrder.getOrderAmount() * 100.0) / 100.0);
		productOrderDTO.setOrderDate(productOrder.getOrderDate());
		productOrderDTO.setFarmerId(productOrder.getFarmerId());

		if(CollectionUtils.isEmpty(productOrder.getOrderProducts())) {
			return productOrderDTO;
		}

		List<OrderProductDTO> orderProductList = productOrder.getOrderProducts().stream().map(orderProduct -> {
			ProductDTO product = productService.findById(orderProduct.getProduct().getProductId(), false);
			return OrderProductDTO.builder()
					.product(product)
					.quantity(orderProduct.getQuantity())
					.price(Math.round(orderProduct.getPrice() * 100.0) / 100.0).build();
		}).collect(Collectors.toList());

		productOrderDTO.setOrderProducts(orderProductList);

		return productOrderDTO;
	}

	private float getTotalAmount(List<OrderProductDTO> orderProductList) {
		return orderProductList.stream()
				.map(this::getProductTotal)
				.reduce(0.0F, Float::sum);

	}

	private float getProductTotal(OrderProductDTO orderProductDTO) {
		return orderProductDTO.getQuantity() * orderProductDTO.getProduct().getPrice();
	}
}
