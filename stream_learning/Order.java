import java.util.List;
class Order {
    private int orderId;
    private List<LineItem> lineItems;

    public Order(int orderId, List<LineItem> lineItems) {
        this.orderId = orderId;
        this.lineItems = lineItems;
    }

    public List<LineItem> getLineItems() { return lineItems; }
}