package com.zensar.stockapplication;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Order(2)
public class MyApplicationRunner1 implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {

		String[] sourceArgs= args.getSourceArgs();
		System.out.println("MyApplicationRunner1 " +Arrays.deepToString(sourceArgs));
	
		
	}

}
