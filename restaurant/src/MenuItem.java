
public class MenuItem {

	private int itemID;
	private String itemName;
	private String category;
	private String description;
	private double price;
	private double cookingTimeMinutes;
	private NutritionInfo nurition;
	
	public MenuItem(int itemID, String itemName, String category, String description,
				    Double price, Double cookTimeMinutes,NutritionInfo nutrition)
	{
		
	}
	
	public int getID(){return itemID;}
	public String getName()	{return itemName;}
	public String getCategory(){return category;}
}
