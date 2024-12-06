package aksp.cw.inventoryservice.service;

import aksp.cw.inventoryservice.repository.ProductRepository;
import aksp.cw.inventoryservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        System.out.println("Products retrieved from database: " + products);
        return products;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product product) {
        System.out.println("Saving product to database: " + product);
        Product savedProduct = productRepository.save(product);
        System.out.println("Product saved to database: " + savedProduct);
        return savedProduct;
    }


    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);
        product.setName(updatedProduct.getName());
        product.setQuantity(updatedProduct.getQuantity());
        product.setPrice(updatedProduct.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public boolean isProductAvailable(Long productId, int quantity) {
        return productRepository.findById(productId)
                .map(product -> product.getQuantity() >= quantity)
                .orElse(false); // Вернуть false, если продукт не найден
    }
}
