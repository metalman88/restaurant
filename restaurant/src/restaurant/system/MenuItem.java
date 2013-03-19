package restaurant.system;

import restuarant.enums.CATEGORYENUMS;

public class MenuItem {

	private int itemID;
	private String itemName;
	private String category;
	private String description;
	private double price;
	private double cookingTimeMinutes;
	private NutritionInfo nutrition;
	
	public MenuItem(int itemID, String itemName, CATEGORYENUMS category, String description,
				    Double price, Double cookingTimeMinutes,NutritionInfo nutrition)
	{
		this.itemID = itemID;
		this.itemName = itemName;
		this.category = category;
		this.description = description;
		this.price = price;
		this.cookingTimeMinutes = cookingTimeMinutes;
		this.nutrition = nutrition;
	}
	
	public int getID(){return itemID;}
	public String getName()	{return itemName;}
	public String getCategory(){return category;}
	public String getDescription(){return description;}
	public double getPrice(){return price;}
	public double getCookingTimeMinutes(){return cookingTimeMinutes;}
	public NutritionInfo getNutritionInfo(){return nutrition;}
	
}
