package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Product;
import lv.venta.repo.iProductRepo;
import lv.venta.services.iCRUDProductService;
import lv.venta.services.iFilteringProductService;

@Service
public class ProductServiceImplWithDB implements iCRUDProductService, iFilteringProductService {
	
	@Autowired
	private iProductRepo productRepo;
	
	@Override
	public ArrayList<Product> filterByPriceLess(float price) {
		// TODO Auto-generated method stub
		return productRepo.findByPriceLessThan(price);
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		// TODO Auto-generated method stub
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product retrieveOneProductById(int id) throws Exception {
		if(productRepo.existsById(id)) {
			return productRepo.findById(id).get();
		} else {
			throw new Exception("Wrong id");
		}
	
	}

	@Override
	public ArrayList<Product> retrieveAllProductsByTitle(String title) throws Exception {
		if(title!=null) {
			ArrayList<Product> allProductsWithTitle = productRepo.findByTitle(title);
			return allProductsWithTitle;
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public Product insertProductByParams(String title, float price, String description, int quantity) {
		Product temp = productRepo.save(new Product(title, price, description, quantity));
		return temp;
	}

	@Override
	public Product updateProductByParams(int id, String title, float price, String description, int quantity) 
			throws Exception {
		
		if(productRepo.existsById(id)) { //Parbaudam vai eksiste tads produkts ar id db
			Product updatedPr = productRepo.findById(id).get(); // pie find by id vienmer vajag vel get()
			updatedPr.setTitle(title);
			updatedPr.setDescription(description);
			updatedPr.setPrice(price);
			updatedPr.setQuantity(quantity);
			return productRepo.save(updatedPr);
		} else {
			throw new Exception("Wrong id");
		}		
	}

	@Override
	public void deleteProductById(int id) throws Exception {
		if(productRepo.existsById(id)) {
			productRepo.deleteById(id);
		} else {
			throw new Exception("Wrong id");
		}
		
	}

}
