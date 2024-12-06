package aksp.cw.orderservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    private final RestTemplate restTemplate;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isProductAvailable(Long productId, int quantity) {
        String url = inventoryServiceUrl + "/" + productId + "/availability?quantity=" + quantity;
        return restTemplate.getForObject(url, Boolean.class);
    }
}