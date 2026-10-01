package CamelInventoryIntegration.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @PostMapping("/inventory")
    public String receiveInventory(@RequestBody Inventory inventory) {

        System.out.println("Sales System received:");
        System.out.println(inventory);
        return "Inventory received by Sales System";
    }
}