package lv.venta.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.models.Product;
import lv.venta.services.iCRUDProductService;
import lv.venta.services.iFilteringProductService;

@Controller
public class FirstController {
	@Autowired
	private iFilteringProductService filterService;
	@Autowired
	private iCRUDProductService crudService;

	@GetMapping("/hello") // tiks izsaukta, ja url bus localhost:8080/hello
	public String helloFunc() {
		System.out.println("Mans pirmais kontrolieris ir nostradajis");
		return "hello-page"; // tiks paradita hello-page.html lapa
	}

	@GetMapping("/msg") // localhost:8080/msg
	public String msgFunc(Model model) {
		model.addAttribute("myMsg", "Te zina no Reina");
		model.addAttribute("myDate", "23.03.2023");
		return "msg-page";// tiks paradita msg-page.html lapa un taja paradisies ari mmyMsg un myDate

	}

	@GetMapping("/product") // localhost:8080/product
	public String prodFunc(Model model) {
		Product prod = new Product("Abols", 3.99f, "Sarkans", 3);
		model.addAttribute("myProduct", prod);
		return "product-page";
	}

	@GetMapping("/productOne") // localhost:8080/productOne?title=Abols
	public String productByParamFunc(@RequestParam("title") String title, Model model) {
		if (title != null) {	
			try {
				ArrayList<Product> temp = crudService.retrieveAllProductsByTitle(title);
				model.addAttribute("myProduct", temp);
				return "all-products-page";
			} catch (Exception e) {
				return "error-page"; // paradis error-page.html lapu
			}

		} return "error-page"; // paradis error-page.html lapu

	}

	// TODO localhost:8080/product/abols

	@GetMapping("/product/{title}") // localhost:8080/productOne?title=Abols
	public String productByParamFunc2(@PathVariable("title") String title, Model model) {
		if (title != null) {	
			try {
				ArrayList<Product> temp = crudService.retrieveAllProductsByTitle(title);
				model.addAttribute("myProduct", temp);
				return "all-products-page";
			} catch (Exception e) {
				return "error-page"; // paradis error-page.html lapu
			}

		} return "error-page"; // paradis error-page.html lapu

	}

	// kontrolieris, kas atgriez visus produktus
	@GetMapping("/allProducts") // localhost:8080/allProducts
	public String allProductsFunc(Model model) {
		model.addAttribute("myAllProducts", crudService.retrieveAllProducts());
		return "all-products-page";
	}

	// kontrolieris, kas izfiltre visus produktus, kuru cena ir mazaka par noteiktu
	@GetMapping("/allProducts/{price}") // localhost:8080/allProducts/1.99
	public String allProductsByPrice(@PathVariable("price") float price, Model model) {
		ArrayList<Product> allProductsWithPriceLess = filterService.filterByPriceLess(price);
			model.addAttribute("myAllProducts", allProductsWithPriceLess);
			return "all-products-page";
		
		//return "error-page";
	}

	@GetMapping("/insert") // localhost:8080/insert
	public String insertProductFunc(Product product) { // tiek padots tuksh produkts
		return "insert-page"; // paradis insert-page.html lapu
	}
	
	//TODO Uzlikt visam validacijam savus zinojumus
	//TODO nodrosinat validaciju ari update gadijuma
	
	@PostMapping("/insert")
	public String insertProductPostFunc(@Valid Product product, BindingResult result) { // tiek sanemts aizpildits produkts
		if(!result.hasErrors()) {
		crudService.insertProductByParams(product.getTitle(), product.getPrice(), product.getDescription(),
				product.getQuantity());
		return "redirect:/allProducts"; // izsaucam get kontrolieri localhost:8080/allProducts
		} else {
			return "insert-page";
		}
	}

	// TODO update
	// TODO izveidot get kontrolieri, kas nolasiis produkta id un pec ta atradis
	// produktu un nosutiis caur model objektu uz frontent
	// + paradit update page
	@GetMapping("/update/{id}") // localhost8080:/update/1
	public String updateProductByIdGetFunc(@PathVariable("id") int id, Model model) {
		try {
				model.addAttribute("product", crudService.retrieveOneProductById(id));
				return "update-page";
			} catch (Exception e) {
				return "error-page";
			}	
	}

	// TODO izveidot update-page.html, kas stradas uz cita endpointa
	// TODO izveidot post kontrolieri, kas sanemto objektu redige ari allProducts
	// sarakstaa
	@PostMapping("/update/{id}")
	public String updateProductByIdPostFunc(@PathVariable("id") int id, @Valid Product product, BindingResult result) { // ienak redigetais
		if(!result.hasErrors()) {
		try {
		Product temp = crudService.updateProductByParams(id, product.getTitle(), 
				product.getPrice(), product.getDescription(), product.getQuantity());
				return "redirect:/product/" + temp.getTitle(); // tiks izsaukts localhost:8080/product/Abols
		} catch (Exception e) {
			return "redirect:/error"; // tiks izsaukts localhost:8080/error
		} 
		} else {
			return "update-page";
		}
		
	}

	@GetMapping("/error")
	public String errorFunc() {
		return "error-page";
	}

	// TODO izveidot delete funkcionalitati
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") int id, Model model) {
		try {
		crudService.deleteProductById(id);
				model.addAttribute("myAllProducts", crudService.retrieveAllProducts());
				return "all-products-page"; // parada all-products-page.html lapu
			} catch (Exception e) {
				return "error-page";
			}
		
		
	}

}
