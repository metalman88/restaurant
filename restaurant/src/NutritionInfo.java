public class NutritionInfo 
{
	private String calories;
	private String totalFat;
	private String saturatedFat;
	private String cholesterol;
	private String sodium;
	private String carbohydrates;
	private String protein;
	
	public NutritionInfo(String calories, String totalFat, String saturatedFat, String cholesterol,
			String sodium, String carbohydrates, String protein)
	{
		this.setCalories(calories);
		this.setCarbohydrates(carbohydrates);
		this.setCholesterol(cholesterol);
		this.setProtein(protein);
		this.setSaturatedFat(saturatedFat);
		this.setSodium(sodium);
		this.setTotalFat(totalFat);
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(String totalFat) {
		this.totalFat = totalFat;
	}

	public String getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(String saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public String getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}

	public String getSodium() {
		return sodium;
	}

	public void setSodium(String sodium) {
		this.sodium = sodium;
	}

	public String getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(String carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}
}