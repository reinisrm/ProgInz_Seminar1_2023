package lv.venta.models;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "customer_table") //DB pusee izveidosies tabula 
@Entity
public class Customer {
	
	@Column(name = "Idc") //DB pusee bus kolona id un bus ka auto increment Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idc;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Surname")
	private String surname;
	
	@OneToMany(mappedBy = "customer") //sasaistam ar mainigo no otras puses
	private Collection<Product> allProducts;

}
