package restaurant.system;

public class Party 
{
	private String name;
	private int partySize;
	
	public Party(String name, int partySize)
	{
		this.name = name;
		this.partySize = partySize;
	}
	
	public String getPartyName()
	{
		return name;
	}
	
	public int getPartySize()
	{
		return partySize;
	}
}
