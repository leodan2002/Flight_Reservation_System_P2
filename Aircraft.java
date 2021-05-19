// Name: An Hoang
// Student ID: 501020760

public class Aircraft  
{
  int numEconomySeats;
  int numFirstClassSeats;
  
  String model;
  
  public Aircraft(int seats, String model)
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
  }

  public Aircraft(int economy, int firstClass, String model)
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
  }
  
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	public int compareTo(Aircraft other) 
	{
		if (this.numEconomySeats == other.numEconomySeats)
		{
			return this.numFirstClassSeats - other.numFirstClassSeats;
		}
		return this.numEconomySeats - other.numEconomySeats;
	}

	public String [][] seatLayout()
	{
  	int firstClassSeats = getNumFirstClassSeats();
  	int maxSeats = getTotalSeats();
	int rows = 0;
	int mark = - 1;
	if (maxSeats > 8)
	{
		rows = 4;
	}
	else
	{
		rows = 2;
	}
  	int collumns = maxSeats / rows;
  	if (firstClassSeats != 0)
  		{
			mark = (firstClassSeats / 4) - 1;
		}
	String [][] seatLayout = new String[rows][collumns];
	for (int i = 0; i <= rows - 1 ; i ++)
	{
		char row = (char) ((char) 65 + i);
		for (int j = 0; j <= collumns - 1 ; j ++)
		{
			String firstClassSign = "";
			if ((j <= mark) && (firstClassSeats > 0))
			{
				firstClassSign = "+";
			}
			seatLayout[i][j] = Integer.toString(j+1) + Character.toString(row) + firstClassSign;
		}
	}
  	return seatLayout;
	}
}