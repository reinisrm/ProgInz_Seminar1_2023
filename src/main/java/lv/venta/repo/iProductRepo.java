package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Product;


public interface iProductRepo extends CrudRepository<Product, Integer> {
	//SELECT * FROM product_table WHERE price < var;
	ArrayList<Product> findByPriceLessThan(float var);
	
	//TODO atlasa pec nosaukuma
	//SELECT * FROM product_table WHERE title='<var>';
	ArrayList<Product> findByTitle(String var);
	
	//TODO atlasa tos produktus, kuru daudzums ir lielaks par 10, bet cena mazaka par 4 eur
	//SELECT * FROM product_table WHERE Quantity > varQ and price < varP;
	ArrayList<Product> findByQuantityGreaterThanAndPriceLessThan(int varQ, float varP);
	
	//@Query(nativeQuery = countBy)
	//ArrayList<Product> functionNameDoesNotMatter();

}
