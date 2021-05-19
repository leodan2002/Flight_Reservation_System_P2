// Name: An Hoang
// Student ID: 501020760

public class LongHaulFlight extends Flight
{
	int firstClassPassengers;
	String seatType;

	public static final String firstClass = "First Class Seat";
	public static final String economy 	= "Economy Seat"; 

	public LongHaulFlight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		super(flightNum, airline,dest, departure, flightDuration, aircraft);
		this.seatType = economy;
	}

	public LongHaulFlight()
	{
		flightNum = "";
		airline = "";
		dest = "";
		departureTime = "";
		flightDuration = 0;
		aircraft = null;
		seatType = economy;
	}

	public boolean reserveSeat()
	{
		return super.reserveSeat();
	}
	
	public boolean reserveSeat(String seatType)
	{
		if (seatType.equals(economy))
		{
			super.reserveSeat();
			return true;
		} 
		else if (seatType.equals(firstClass))
		{
			int firstClassSeats = aircraft.getNumFirstClassSeats();
			if (firstClassSeats > firstClassPassengers)
			{
				firstClassPassengers ++;
				return true;
			}
		}
		return false;
	}

	public void cancelSeat()
	{
		super.cancelSeat();
	}
	
	public void cancelSeat(String seatType) 
	{
		if (firstClassPassengers > 0 && seatType.equals(firstClass )) 
		{
			firstClassPassengers--;
		} 
		else 
		{
			super.cancelSeat();
		}
	}

	public int getPassengerCount()
	{
		int economySeatsNum = getNumPassengers();;
		return economySeatsNum + firstClassPassengers;
	}
	
	public Type getFlightType() // Helps distinguish between a regular flight object and a long haul flight
	{
		return Type.LONGHAUL;
	}

	public String toString()
	{
		return super.toString() + "\t LongHaul";
	}
}