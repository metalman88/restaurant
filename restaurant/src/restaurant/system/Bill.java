package restaurant.system;
import java.util.ArrayList;
import java.util.List;

/**
 * A fake billing class which takes in a list of OrderChunk and compute the
 * total amount
 * @author tbo
 *
 */
public class Bill
{
	
	private ArrayList<OrderChunk> orderChunk;
	
	public Bill(ArrayList<OrderChunk> orderChunk)
	{
		this.orderChunk = orderChunk;
	}
	
	public String billInfo()
	{
		double total = 0;
		int maxLength = computeMaxLength();
		StringBuilder builder = new StringBuilder("-----Zot Restaurant----");
		builder.append("\n");
		
		for (OrderChunk order : orderChunk)
		{
			List<SingleItemWithNote> items = order.getItems();
			for (SingleItemWithNote item : items)
			{
				String name = item.getItem().getName();
				while (name.length() < maxLength)
					name += " ";
				
				builder.append(name);
				builder.append(item.getItem().getPrice());
				builder.append("\n");
				total += item.getItem().getPrice();
			}
		}
		
		builder.append("\n");
		String totalAmount = "Total";
		while (totalAmount.length() < maxLength)
			totalAmount += " ";
		builder.append(totalAmount).append(total);
		
		return builder.toString();		
	}
	
	private int computeMaxLength()
	{
		int maxLength = 0;
		for (OrderChunk order : orderChunk)
		{
			for (SingleItemWithNote item : order.getItems())
			{
				if (item.getItem().getName().length() > maxLength)
					maxLength = item.getItem().getName().length();
			}
		}
		
		return maxLength + 10;
	}
	
}
