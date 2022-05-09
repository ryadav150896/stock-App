package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.repository.StockRepository;
@Service
public class StockServiceImpl implements StockService {
	@Autowired
	private StockRepository stockRepository;
	
	
	//private ModelMapper modelMapper=new ModelMapper();
	@Autowired
	private ModelMapper modelMapper;
	/*static List<Stock> stocks = new ArrayList();
	static {
		stocks.add(new Stock(1L, "Rjio", "bse", 321));
		stocks.add(new Stock(2L, "zensar", "bse", 325));

	}*/
	@Override
	public List<StockDto> getAllStocks(int pageNumber,int pageSize,String sortBy) {
		// TODO Auto-generated method stub
		 Page<Stock> pageStocks=stockRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(sortBy)));
		 List<Stock> content= pageStocks.getContent();
		 List<StockDto> stockResponses = new ArrayList<>();

			for (Stock stock : content) {
				/*StockResponse mapToResponse = mapToResponse(stock);
				stockResponses.add(mapToResponse);*/
				StockDto mapToResponse= modelMapper.map(stock,StockDto.class);
				stockResponses.add(mapToResponse);
			}
			return stockResponses;
		}
	
	
	

	public StockDto getStock(long stockId) throws InvalidStockIdException {
		
		Optional<Stock> optStock = stockRepository.findById(stockId);
		
		if(optStock.isPresent()) {
			Stock stock=optStock.get();
			
			return modelMapper.map(stock,StockDto.class);
		}else {
			
			throw new InvalidStockIdException("Stock is not present of given stockId "+stockId);
		}
		//StockResponse stockResponse = new StockResponse();
		//StockDto newStock=modelMapper.map(stock, StockDto.class);
		
		/*stockResponse.setStockId(stock.getStockId());
		stockResponse.setName(stock.getName());
		stockResponse.setPrice(stock.getPrice());
		stockResponse.setMarketName(stock.getMarketName());*/

		
	}
		/*Optional<Stock> optStock=stockRepository.findById(stockId);
		if(optStock.isPresent()) {
			return optStock.get();
		}
		else {return optStock.orElseGet( ()->{ return new Stock();});
		}/*
}		// TODO Auto-generated method stub
/*Optional<Stock> stock1=stocks.stream().filter(stock->stock.getStockId()==stockId).findAny();
		
		
		if(stock1.isPresent()) {
			return stock1.get();
		}else {
			return stock1.orElseGet( ()->{ return new Stock();});}}
		
	*/

	
	public StockDto createStock(StockDto stockDto, String token) {
		
		//Stock newStock = mapTostock(stock);
		Stock newStock=modelMapper.map(stockDto, Stock.class);
		// return stockRepository.save(stock);

		if (token.equals("ry43099")) {
			 Stock save = stockRepository.save(newStock);
			return modelMapper.map(save,StockDto.class);

		} else {
			return null;
		}
}
	@Override
	public String deleteStock(long stockId) {
		
		stockRepository.deleteById(stockId);
		return "stock deleted succesfuly of stockId " +stockId;
	}
	/*	for (Stock stock : stocks) {

			if (stock.getStockId() == stockId ) {
				stocks.remove(stock);

				return "stock id deleted " + stockId;
			}
		}
		return "sorry, stockId is not present";
	}*/

	@Override
	public StockDto updateStocks(int stockId, Stock stock) throws InvalidStockIdException {
		
		StockDto stockDto = getStock(stockId);
		
		
		//Stock stock2 = mapTostock(stockResponse);
      Stock stock2=modelMapper.map(stockDto, Stock.class);
		
		if(stock2!=null) {
			stock2.setPrice(stock.getPrice());
			stock2.setName(stock.getName());
			stock2.setMarketName(stock.getMarketName());
			//stock2.setStockId(stockId);
			Stock stock3 = stockRepository.save(stock2);
			return modelMapper.map(stock3, StockDto.class);
			
		}
		
		
		return null;
	}
		
	

		/*Stock availablestock = getStock(stockId);
		availablestock.setMarketName(stock.getMarketName());
		availablestock.setName(stock.getName());
		availablestock.setPrice(stock.getPrice());

		return availablestock;
	}*/

	@Override
	public String deleteAllStocks() {
		stockRepository.deleteAll();
		return "All stocks deleted succesfully";
		//stocks.removeAll(stocks);
		//return "succesfully deleted";
	}

	/*private Stock mapTostock(StockRequest stockRequest) {
		
		Stock newStock=new Stock();
		 
		 newStock.setMarketName(stockRequest.getMarketName());
	     newStock.setName(stockRequest.getName());
	     newStock.setPrice(stockRequest.getPrice());
		return newStock;
	}
 private Stock  mapTostock(StockResponse stockResponse) {
		
	 Stock newStock = new Stock();
		 
	 newStock.setMarketName(stockResponse.getMarketName());
	 newStock.setName(stockResponse.getName());
	 newStock.setPrice(stockResponse.getPrice());
		return newStock;

}
 private StockResponse mapToResponse(Stock stock) {
		
	 StockResponse stockResponse = new StockResponse();

		 
	 stockResponse.setMarketName(stock.getMarketName());
	 stockResponse.setName(stock.getName());
	 stockResponse.setPrice(stock.getPrice());
		return stockResponse;

}
}*/

public List<StockDto> getStocksByName(String stockName){
	
	List<Stock> findByName=stockRepository.findStocksByItsName(stockName);
	
	List<StockDto> stocks=new ArrayList<StockDto>();
	for(Stock stock1:findByName) {
		
		stocks.add(modelMapper.map(stock1,StockDto.class));
	}return stocks;
	
}
public List<StockDto> getStocksByNameAndPrice(String stockName,long price){
	
	List<Stock> findByNameAndPrice=stockRepository.findStocksByItsNameAndPrice(stockName,price);
	
	List<StockDto> stocks=new ArrayList<StockDto>();
	for(Stock stock1:findByNameAndPrice) {
		
		stocks.add(modelMapper.map(stock1,StockDto.class));
	}return stocks;
	
}
	
}