package com.kisaan.jai.repository;

import com.kisaan.jai.entity.OrderProduct;
import com.kisaan.jai.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{
}
