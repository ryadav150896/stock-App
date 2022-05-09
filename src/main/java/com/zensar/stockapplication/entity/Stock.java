package com.zensar.stockapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel("This is the Stcok Model")
@Entity(name = "Mystock") //to map the class with database
@Table(name = "Stock")
//@NamedQueries(value = { @NamedQuery ( name= " Stock.findStocksByitsNameAndPrice",query=  "FROM Stock s where s.name=?1 and s.price=?2"),@NamedQuery(name= " Stock.findStocksByitsNam", query="FROM Mystock s where s.name=?1")})
//@NamedQuery(name =" Stock.findStocksByitsNameAndPrice" ,query = "FROM Mystock s where s.name=?1 and s.price=?2")
//@NamedQuery(name =" Stock.findStocksByitsName" ,query = "FROM Mystock s where s.name=?1")
//@NamedNativeQuery(name =" Stock.findStocksByitsName" ,query = "select * FROM Stock where name=?1",resultClass = Stock.class)
public class Stock {
	//@ApiModelProperty("stockId of Integer Type")
	@Id //primary key ke liye
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long stockId;
	//@ApiModelProperty("Stock Name of String Type")
	private String name;
	//@ApiModelProperty("marketName of String Type")
	private String marketName;
	//@ApiModelProperty("price of long Type")
	private long price;
}
