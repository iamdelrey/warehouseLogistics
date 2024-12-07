package aksp.cw.deliveryservice.service;

import aksp.cw.deliveryservice.model.Delivery;
import aksp.cw.deliveryservice.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
    }

    public Delivery createDelivery(Delivery delivery) {
        delivery.setCreatedAt(java.time.LocalDateTime.now());
        delivery.setUpdatedAt(java.time.LocalDateTime.now());
        return deliveryRepository.save(delivery);
    }

    public Delivery updateDelivery(Long id, Delivery updatedDelivery) {
        Delivery existingDelivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));

        if (updatedDelivery.getStatus() != null) {
            existingDelivery.setStatus(updatedDelivery.getStatus());
        }

        return deliveryRepository.save(existingDelivery);
    }


    public void deleteDelivery(Long id) {
        if (!deliveryRepository.existsById(id)) {
            throw new RuntimeException("Delivery not found with id: " + id);
        }
        deliveryRepository.deleteById(id);
    }
}
