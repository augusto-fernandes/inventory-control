package br.com.inventorycontrol.repository;


import br.com.inventorycontrol.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
