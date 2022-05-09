package com.zensar.stockapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.service.StockService;
//@Controller
@RestController
@RequestMapping("/stocks")
//@CrossOrigin("*")-to allow the request from other url
public class StockController {
	//layered architecture
    @Autowired
	private StockService stockService;
	/*static List<Stock> stocks = new ArrayList();
	static {
		stocks.add(new Stock(1L, "Rjio", "bse", 321));
		stocks.add(new Stock(2L, "zensar", "bse", 325));

	}*/

	/*
	 * public StockController() { stocks.add(new Stock(1L,"Rjio","bse",321));
	 * stocks.add(new Stock(2L,"zensar","bse",325)); }
	 */
	// read all stocks
	// @GetMapping("/stocks")
	//@ResponseBody
    //@ApiOperation(value="Getting all the stocks Info")
	@RequestMapping(method = RequestMethod.GET, produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public  List<StockDto> getAllStocks(@RequestParam(value="pageSize",defaultValue = "5",required = false) int pageSize,@RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,@RequestParam(value="sortBy",defaultValue = "stockId",required = false) String sortBy) {
          System.out.println(pageNumber);
          System.out.println(pageSize);
          System.out.println(sortBy);
		return stockService.getAllStocks(pageNumber,pageSize,sortBy);

	}
	// handler method
	// read specific stock
	// @GetMapping("/stocks/{stockId}")
	/*
	 * @RequestMapping(value = "/{stockId}", method = RequestMethod.GET) public
	 * Stock getStock(@PathVariable long stockId) {
	 * 
	 * for(Stock stock : stocks) {
	 * 
	 * if (stock.getStockId() == stockId) { return stock; } } return null; }
	 */
    //@ApiOperation(value="Getting a specific stock")
   //   @ApiResponse(code = 200, message="got the stock of given stockId")
	@RequestMapping(value = "/{stockId}", method = RequestMethod.GET, produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public StockDto getStock(/*@ApiParam("stock id should be grater than 1")*/ @PathVariable long stockId) throws InvalidStockIdException {

		return stockService.getStock(stockId);
	}
	// create new stock
   // @ApiOperation(value="Create New stock")
	@PostMapping (produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	//@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto stock, @RequestHeader("auth-token") String token) {
		StockDto createstock=stockService.createStock(stock, token);
        if(createstock==null) {
			return new ResponseEntity<StockDto>( HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<StockDto>(createstock, HttpStatus.CREATED);
	}

	/*
	 * //@GetMapping("/stocks")
	 * 
	 * @RequestMapping(value="/stocks", method=RequestMethod.GET) public Stock
	 * getStock(@RequestParam(required=false, value="id", defaultValue ="1") long id
	 * ) {
	 * 
	 * for(Stock stock:stocks) {
	 * 
	 * if(stock.getStockId()==id) { return stock; } } return null; }
	 */
	// delete specific stock
   // @ApiOperation(value="Delete specific stock")
	@DeleteMapping(value="/{stockId}", produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public String deleteStock(@PathVariable long stockId) {

		/*for (Stock stock : stocks) {

			if (stock.getStockId() == stockId ) {
				stocks.remove(stock);

				return "stock id deleted " + stockId;
			}
		}
		return "sorry, stockId is not present";*/
		return stockService.deleteStock(stockId);
	}
    //@ApiOperation(value="Update available stock")
	@PutMapping(value="/{stockId}", produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public StockDto updateStocks(@PathVariable int stockId, @RequestBody Stock stock) throws InvalidStockIdException {

		/*Stock availablestock = getStock(stockId);
		availablestock.setMarketName(stock.getMarketName());
		availablestock.setName(stock.getName());
		availablestock.setPrice(stock.getPrice());

		return availablestock;*/
		return stockService.updateStocks(stockId, stock);

	}

	/*
	 * @RequestMapping(value="/test", method=
	 * {RequestMethod.GET,RequestMethod.POST}) public void test() {
	 * System.out.println("inside test"); }
	 */
	// delete all stocks
   // @ApiOperation("Delete All the stocks")
	@DeleteMapping
	public ResponseEntity<String> deleteAllStocks() {

		String result=stockService.deleteAllStocks();

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
    
   // @ApiOperation(value="Getting a specific stock based on name")
   // @ApiResponse(code = 200, message="got the stock of given name")
	@RequestMapping(value = "name/{stockName}", method = RequestMethod.GET, produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<StockDto> getStockByName(@PathVariable("stockName") String stockName) {

		return stockService.getStocksByName(stockName);
	}
    
   // @ApiOperation(value="Getting a specific stock based on name and price")
    //@ApiResponse(code = 200, message="got the stock of given name and price")
	@RequestMapping(value = "name/{stockName}/price/{stockPrice}", method = RequestMethod.GET, produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<StockDto> getStock(@PathVariable("stockName") String stockName,@PathVariable("stockPrice") long price) {

		return stockService.getStocksByNameAndPrice(stockName,price);
	}
/*	@ExceptionHandler(InvalidStockIdException.class)
	
	public String handleException() {
		
		
		return "Invalid stockId";
	}*/
}
