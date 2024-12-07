package aksp.cw.analyticsservice.controller;

import aksp.cw.analyticsservice.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/total-orders")
    public int getTotalOrders() {
        return analyticsService.getTotalOrders();
    }

    @GetMapping("/total-products-sold")
    public int getTotalProductsSold() {
        return analyticsService.getTotalProductsSold();
    }
}
