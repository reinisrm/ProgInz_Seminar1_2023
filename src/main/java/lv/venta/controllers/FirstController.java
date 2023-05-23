package lv.venta.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.models.Product;
@Controller
public class FirstController {
	
	
	private ArrayList<Product> allProducts
	= new ArrayList<>(Arrays.asList(
			new Product(1, "Abols", 3.99f, "Sarkans", 3),
			new Product(2, "Burkans", 0.33f, "Oranzs", 2),
			new Product(3, "Gurkis", 1.22f, "Zals", 6)));
	@GetMapping("/hello") //tiks izsaukta, ja url bus localhost:8080/hello
	public String helloFunc() {
		System.out.println("Mans pirmais kontrolieris ir nostradajis");
		return "hello-page"; //tiks paradita hello-page.html lapa
	}
	
	@GetMapping("/msg") //localhost:8080/msg
	public String msgFunc(Model model) {
		model.addAttribute("myMsg", "Te zina no Reina");
		model.addAttribute("myDate", "23.03.2023");
		return "msg-page";//tiks paradita msg-page.html lapa un taja paradisies ari mmyMsg un myDate
		
	}
	
	@GetMapping("/product") //localhost:8080/product
	public String prodFunc(Model model) {
		Product prod = new Product(1, "Abols", 3.99f, "Sarkans", 3);
		model.addAttribute("myProduct", prod);
		return "product-page";
	}
	
	@GetMapping("/productOne") //localhost:8080/productOne?title=Abols
	public String productByParamFunc(@RequestParam("title") String title, Model model) {
		if(title!=null) {
			for(Product temp: allProducts) {
				if(temp.getTitle().equals(title)) {
					model.addAttribute("myProduct", temp);
					return "product-page";
				}
			}
		}
		return "error-page"; // paradis error-page.html lapu
	}
	
	//TODO localhost:8080/product/abols
	
	@GetMapping("/product/{title}") //localhost:8080/productOne?title=Abols
	public String productByParamFunc2(@PathVariable("title") String title, Model model) {
		if(title!=null) {
			for(Product temp: allProducts) {
				if(temp.getTitle().equals(title)) {
					model.addAttribute("myProduct", temp);
					return "product-page";
				}
			}
		}
		return "error-page"; // paradis error-page.html lapu
	}
	
	
}
