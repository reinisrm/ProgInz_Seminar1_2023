package lv.venta.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Product {
	
        private int id;
        
        @NotNull
        @Pattern(regexp = "[A-Z] {1} [a-z\\ ]+")
        @Size(min = 3, max = 30)
        private String title;
        
        @Min(value = 0)
        @Max(value = 10000)
        private float price;
        
        @NotNull
        @Pattern(regexp = "[A-Z] {1} [a-z\\ ]+")
        @Size(min = 3, max = 30)   
        private String description;
        
        @Min(value = 0)
        @Max(value = 1000)
        private int quantity;
        
        
        private static int idCounter = 0;
        public int getId() {
                return id;
        }
        public void setId() {
                this.id = idCounter++;
        }
        public String getTitle() {
                return title;
        }
        public void setTitle(String title) {
                this.title = title;
        }
        public float getPrice() {
                return price;
        }
        public void setPrice(float price) {
                this.price = price;
        }
        public String getDescription() {
                return description;
        }
        public void setDescription(String description) {
                this.description = description;
        }
        public int getQuantity() {
                return quantity;
        }
        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }
        @Override
        public String toString() {
                return "Product [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description
                                + ", quantity=" + quantity + "]";
        }
        public Product(String title, float price, String description, int quantity) {
        		setId();              
                this.title = title;
                this.price = price;
                this.description = description;
                this.quantity = quantity;
        }
     
        
        
        public Product() {}

}