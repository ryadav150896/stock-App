package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;

public interface StockService {
	
	 List<StockDto> getAllStocks(int pageNumber,int pageSize,String sortBy);
	 StockDto getStock( long stockId) throws InvalidStockIdException;
	 StockDto createStock( StockDto stock,String token) ;
	 String deleteStock( long stockId);
	 StockDto updateStocks( int stockId, Stock stock) throws InvalidStockIdException;
	 String deleteAllStocks();
	 List<StockDto> getStocksByName(String stockName);
	 List<StockDto> getStocksByNameAndPrice(String stockName,long price);
}