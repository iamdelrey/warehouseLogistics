package aksp.cw.analyticsservice.service;

import aksp.cw.analyticsservice.client.OrderServiceClient;
import aksp.cw.analyticsservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {
    private final OrderServiceClient orderServiceClient;

    public AnalyticsService(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    public int getTotalOrders() {
        List<Order> orders = orderServiceClient.getAllOrders();
        return orders.size();
    }

    public int getTotalProductsSold() {
        List<Order> orders = orderServiceClient.getAllOrders();
        return orders.stream().mapToInt(Order::getQuantity).sum();
    }
}
