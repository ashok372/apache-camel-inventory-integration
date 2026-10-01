package CamelInventoryIntegration.demo;

public class Inventory {

    private String productId;
    private String productName;
    private String outlet;
    private int quantity;

    public Inventory(String productId, String productName,
                     String outlet, int quantity) {

        this.productId = productId;
        this.productName = productName;
        this.outlet = outlet;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }

    public String getOutlet() {
        return outlet;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", outlet='" + outlet + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}


