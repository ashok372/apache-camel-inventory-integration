package CamelInventoryIntegration.demo;

public class SalesRequest {

    private String productId;
    private String outlet;
    private int requestedQuantity;

    public SalesRequest(String productId, String outlet, int requestedQuantity) {
        this.productId = productId;
        this.outlet = outlet;
        this.requestedQuantity = requestedQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getOutlet() {
        return outlet;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    @Override
    public String toString() {
        return "SalesRequest{" +
                "productId='" + productId + '\'' +
                ", outlet='" + outlet + '\'' +
                ", requestedQuantity=" + requestedQuantity +
                '}';
    }
}