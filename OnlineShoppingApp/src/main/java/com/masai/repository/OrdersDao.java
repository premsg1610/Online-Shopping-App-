package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Orders;

@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer>{

}
