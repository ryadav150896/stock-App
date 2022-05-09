package com.zensar.stockapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zensar.stockapplication.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	//@Query("FROM Mystock s where s.name=?1")
	@Query(value = "select * From Stock where name=:name",nativeQuery = true)
	List<Stock> findStocksByItsName(@Param("name")String name);
	//@Query("FROM Mystock s where s.name=?1 and s.price=?2")
	@Query(value = "select * From Stock where name=:name and price=:price",nativeQuery = true)
	List<Stock> findStocksByItsNameAndPrice(@Param("name")String name,@Param("price")long price);
	

}
