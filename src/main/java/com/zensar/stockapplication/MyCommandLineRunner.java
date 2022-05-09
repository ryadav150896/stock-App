package com.zensar.stockapplication;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Order(1)
public class MyCommandLineRunner implements CommandLineRunner {

	
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MycommandLineRunner " +Arrays.toString(args));
		
	}

}
