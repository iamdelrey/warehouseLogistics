package aksp.cw.inventoryservice.graphql;

import aksp.cw.inventoryservice.model.Product;
import aksp.cw.inventoryservice.service.ProductService;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;

@Component
public class ProductMutation {

    private final ProductService productService;

    public ProductMutation(ProductService productService) {
        this.productService = productService;
    }

    @SchemaMapping(typeName = "Mutation", field = "createProduct")
    public Product createProduct(String name, int quantity, double price) {
        System.out.println("createProduct called with params: name=" + name + ", quantity=" + quantity + ", price=" + price);

        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);

        Product savedProduct = productService.createProduct(product);
        System.out.println("Saved product in mutation: " + savedProduct);

        return savedProduct;
    }


    @SchemaMapping(typeName = "Mutation", field = "updateProduct")
    public Product updateProduct(Long id, String name, Integer quantity, Double price) {
        Product product = productService.getProductById(id);
        if (name != null) product.setName(name);
        if (quantity != null) product.setQuantity(quantity);
        if (price != null) product.setPrice(price);
        return productService.updateProduct(id, product);
    }

    @SchemaMapping(typeName = "Mutation", field = "deleteProduct")
    public boolean deleteProduct(Long id) {
        productService.deleteProduct(id);
        return true;
    }
}
