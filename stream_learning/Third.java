import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
class Third
{
	public static void main(String[] args)
	{
		// --- Generating Mock Data ---
        List<Order> orders = Arrays.asList(
            new Order(1, Arrays.asList(
                new LineItem("iPhone 15", 999.0),
                new LineItem("MacBook Air", 1200.0)
            )),
            new Order(2, Arrays.asList(
                new LineItem("iPhone 15", 999.0), // Duplicate product
                new LineItem("Sony Headphones", 350.0)
            )),
            new Order(3, Arrays.asList(
                new LineItem("Monitor 4K", 600.0),
                new LineItem("MacBook Pro", 2500.0),
                new LineItem("Mechanical Keyboard", 150.0)
            ))
        );

        List<String> top3Products = 
        	orders.stream()
        		.flatMap(order -> order.getLineItems().stream())
        		.sorted(Comparator.comparingDouble(LineItem::getPrice).reversed())
        		.map(LineItem::getProductName)
        		.distinct()
        		.limit(3)
        		.collect(Collectors.toList());
        System.out.println(top3Products);
	}
}