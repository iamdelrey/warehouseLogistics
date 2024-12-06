package aksp.cw.orderservice.graphql;

import aksp.cw.orderservice.model.Order;
import aksp.cw.orderservice.service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderResolver {

    private final OrderService orderService;

    public OrderResolver(OrderService orderService) {
        this.orderService = orderService;
    }

    @QueryMapping
    public List<Order> orders() {
        return orderService.getAllOrders();
    }

    @QueryMapping
    public Order order(@Argument Long id) {
        return orderService.getOrderById(id);
    }

    @MutationMapping
    public Order createOrder(
            @Argument Long userId,
            @Argument Long productId,
            @Argument int quantity,
            @Argument String status
    ) {
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus(status);
        return orderService.createOrder(order);
    }

    @MutationMapping
    public Order updateOrder(
            @Argument Long id,
            @Argument Long userId,
            @Argument Long productId,
            @Argument Integer quantity,
            @Argument String status
    ) {
        Order existingOrder = orderService.getOrderById(id);
        if (userId != null) existingOrder.setUserId(userId);
        if (productId != null) existingOrder.setProductId(productId);
        if (quantity != null) existingOrder.setQuantity(quantity);
        if (status != null) existingOrder.setStatus(status);
        return orderService.updateOrder(id, existingOrder);
    }
    @MutationMapping
    public boolean deleteOrder(@Argument Long id) {
        orderService.deleteOrder(id);
        return true;
    }
}
