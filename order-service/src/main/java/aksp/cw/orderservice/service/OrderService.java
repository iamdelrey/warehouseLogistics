package aksp.cw.orderservice.service;

import aksp.cw.orderservice.model.Order;
import aksp.cw.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient; // Добавлено

    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient; // Внедрение зависимости
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public Order createOrder(Order order) {
        boolean isAvailable = inventoryServiceClient.isProductAvailable(order.getProductId(), order.getQuantity());
        if (!isAvailable) {
            throw new RuntimeException("Product is not available in the requested quantity.");
        }
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = getOrderById(id);
        if (updatedOrder.getProductId() != null) {
            existingOrder.setProductId(updatedOrder.getProductId());
        }
        if (updatedOrder.getQuantity() != null) {
            existingOrder.setQuantity(updatedOrder.getQuantity());
        }
        if (updatedOrder.getStatus() != null) {
            existingOrder.setStatus(updatedOrder.getStatus());
        }
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}