package com.example.api;

import com.example.api.Reprositories.productRepo;
import com.example.api.projections.projectionbyattr;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {
	@Autowired
	productRepo pt;
	@Test
	void contextLoads() {
	}
//	@Test
//	@Transactional
//	public void tests(){
////	projectionbyattr p=pt.something(1L);
////	System.out.println(p.getTitle());
////	System.out.println(p.getDescription());
////	System.out.println("debug");
////		projectionbyattr p2=pt.something(2L);
////		System.out.println(p2.getTitle());
////		System.out.println(p2.getDescription());
//	}
}
