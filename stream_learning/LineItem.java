class LineItem {
    private String productName;
    private double price;

    public LineItem(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() { return productName; }
    public double getPrice() { return price; }
}