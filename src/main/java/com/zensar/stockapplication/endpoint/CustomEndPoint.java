package com.zensar.stockapplication.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="custom")
public class CustomEndPoint {
	@ReadOperation
	public String myOwnCustomEndpoint() {
		
		return "my own endpoint";
	}

}
