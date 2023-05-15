package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.OrderProductDTO;
import com.kisaan.jai.entity.OrderProduct;
import com.kisaan.jai.repository.OrderProductRepository;
import com.kisaan.jai.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProductServiceImpl implements OrderProductService {

	@NotNull
	private final OrderProductRepository orderProductRepository;

	@Override
	public OrderProduct save(OrderProduct orderProduct) {
		return orderProductRepository.save(orderProduct);
	}

	@Override
	public OrderProduct update(Long aLong, OrderProduct orderProduct) {
		return null;
	}

	@Override
	public void delete(Long aLong) {

	}
}
