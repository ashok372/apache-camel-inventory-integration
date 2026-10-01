package CamelInventoryIntegration.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class InventoryRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("timer:inventory?period=5000")

                .process(exchange -> {

                    Inventory inventory = new Inventory(
                            "P101",
                            "Laptop",
                            "Chennai",
                            10
                    );

                    SalesRequest salesRequest = new SalesRequest(
                            "P101",
                            "Chennai",
                            4
                    );

                    exchange.getIn().setBody(inventory);
                    exchange.setProperty("salesRequest", salesRequest);
                })

                // Check outlet and stock
                .choice()

                .when(exchange -> {

                    Inventory inventory =
                            exchange.getIn().getBody(Inventory.class);

                    SalesRequest salesRequest =
                            exchange.getProperty(
                                    "salesRequest",
                                    SalesRequest.class
                            );

                    String outlet = inventory.getOutlet();

                    boolean allowedOutlet =
                            outlet.equals("Chennai")
                                    || outlet.equals("Coimbatore")
                                    || outlet.equals("Bangalore");

                    boolean enoughStock =
                            inventory.getQuantity()
                                    >= salesRequest.getRequestedQuantity();

                    return allowedOutlet && enoughStock;
                })

                .process(exchange -> {

                    Inventory inventory =
                            exchange.getIn().getBody(Inventory.class);

                    SalesRequest salesRequest =
                            exchange.getProperty(
                                    "salesRequest",
                                    SalesRequest.class
                            );

                    int remainingStock =
                            inventory.getQuantity()
                                    - salesRequest.getRequestedQuantity();

                    inventory.setQuantity(remainingStock);
                })

                // Convert Inventory Java object to JSON
                .marshal().json()

                // Send updated inventory to Sales System
                .to("http://localhost:8080/sales/inventory")


                .otherwise()

                .to("log:sale-rejected")

                .end();
    }
}