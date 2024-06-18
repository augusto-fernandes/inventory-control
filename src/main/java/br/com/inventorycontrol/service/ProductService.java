package br.com.inventorycontrol.service;

import br.com.inventorycontrol.model.Product;
import br.com.inventorycontrol.repository.ProductRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }
    public Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseThrow(() -> new NoResultException("Nenhum usuário encontrado com o ID: " + id));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
