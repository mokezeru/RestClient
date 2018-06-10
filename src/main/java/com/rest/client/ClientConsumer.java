package com.rest.client;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.rest.client.model.Product;

@Controller
public class ClientConsumer {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	@RequestMapping(value="/product")
	public String listProduct(Model model) {
		String url = "http://localhost:8080/api/listProducts";
		List<LinkedHashMap> products = restTemplate.getForObject(url, List.class);
		//System.out.println(products.get(0).getProductName());
		
		model.addAttribute("products", products);
		
		return "listProduct";
	}
	@RequestMapping(value="/person")
	public String listPerson(Model model) {
		String url = "http://localhost:8080/api/listPersons";
		List<LinkedHashMap> persons = restTemplate.getForObject(url, List.class);
		model.addAttribute("persons", persons);
		
		return "listPerson";
	}
	@RequestMapping(value="/order")
	public String listOrder(Model model) {
		String url = "http://localhost:8080/api/listOrders";
		List<LinkedHashMap> orders = restTemplate.getForObject(url, List.class);
		model.addAttribute("orders", orders);
		
		return "listOrder";
	}
	
	@RequestMapping(value="/addProduct")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "createProduct";
	}
	
	@RequestMapping(value="/client/addProduct",method=RequestMethod.POST)
	public String addProductPost(Product product) {
		
		System.out.println(">>>>>>>>>product creation method>>>>>>>"+product.getProductName());
		Product pp = new Product();
		pp.setProductName("llllll");
		//String url = "http://localhost:8080/api/createProduct";
		restTemplate.postForObject("http://localhost:8080/api/createProduct", product, Product.class);
		
		System.out.println(">>>>>>>>>product creation method>>>>>>>"+product.getProductName());
		
		return "createProduct";
	}

	

}
