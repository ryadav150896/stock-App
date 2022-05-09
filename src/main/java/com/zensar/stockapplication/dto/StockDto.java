package com.zensar.stockapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class StockDto {
	
	//@ApiModelProperty("stockId of Integer Type")
	//@Id //primary key ke liye
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long stockId;
	//@ApiModelProperty("Stock Name of String Type")
	private String name;
	//@ApiModelProperty("marketName of String Type")
	private String marketName;
	//@ApiModelProperty("price of long Type")
	private long price;

}
