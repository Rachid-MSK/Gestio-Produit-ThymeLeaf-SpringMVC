package org.sid;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class MyCatalogueApplication implements CommandLineRunner {
	@Autowired
	private ProduitRepository produitRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyCatalogueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		produitRepository.save(new Produit(null, "PC PORTABLE", 8000, 1));
		produitRepository.save(new Produit(null, "IMPRIMANTE", 7000, 9));
		produitRepository.save(new Produit(null, "SMART PHONE", 2000, 5));
		produitRepository.save(new Produit(null, "IPHONE", 1500, 6));
		
		Page<Produit> produits= produitRepository.findByDesignationContains("H", PageRequest.of(0, 3));
		System.out.println(produits.getTotalElements());
		System.out.println(produits.getTotalPages());
		produits.forEach(p->{
			System.out.println(p.toString());
		});
		
		System.out.println("***********************************************************");
		
		Page<Produit> prods= produitRepository.chercher("%H%", 1500, PageRequest.of(0, 4));
		System.out.println(prods.getSize());
		System.out.println(prods.getTotalElements());
		System.out.println(prods.getTotalPages());
		
		prods.getContent().forEach(p->{
			System.out.println(p.toString());
		});
		
	}

}
