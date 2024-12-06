package aksp.cw.inventoryservice.graphql;

import aksp.cw.inventoryservice.model.Product;
import aksp.cw.inventoryservice.service.ProductService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductResolver {

    private final ProductService productService;

    public ProductResolver(ProductService productService) {
        this.productService = productService;
    }

    @SchemaMapping(typeName = "Query", field = "products")
    public List<Product> products() {
        List<Product> products = productService.getAllProducts();
        System.out.println("Fetched products: " + products);
        return products;
    }

}

