package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.Product;

public interface iFilteringProductService {
	ArrayList<Product> filterByPriceLess(float price);
}
