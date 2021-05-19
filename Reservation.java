// Name: An Hoang
// Student ID: 501020760

public class Reservation
{
	String flightNum;
	String flightInfo;
	String name;
	String passengerName; 
	int passport;
	String passengerPassport;
	int seatID;
	String seat;
	boolean firstClass;
	boolean ticket = false;
	
	public Reservation(String flightNum, String info)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;
		this.ticket = false;
	}

	public Reservation(String flightNum, String passengerName, String passengerPassport,String seat, String info)
	{
		this.flightNum = flightNum;
		this.passengerName = passengerName;
		this.passengerPassport = passengerPassport;
		this.seat = seat;
		this.flightInfo = info;
	}

	public String getFlightNum()
	{
		return flightNum;
	}
	
	public String getFlightInfo()
	{
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo)
	{
		this.flightInfo = flightInfo;
	}

	public void print()
	{
		System.out.println(flightInfo + "\t"+ passengerName+ "\t"+ passengerPassport+ "\t"+ seat);
	}

	public boolean isFirstClass()
	{
		return firstClass;
	}

	public void setFirstClass()
	{
		this.firstClass = true;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getPassport(){
		return passport;
	}

	public void setPassport(int passport){
		this.passport = passport;
	}

	// This override method compare Reservation objects for equality based on  passengerName, passengerPassport, and flightNum.
	public boolean equals(Object other)
	{
		if (other instanceof Reservation)
		{
			Reservation temp = (Reservation) other;
			return (this.passengerName.equals(temp.passengerName) && (this.passengerPassport.equals(temp.passengerPassport)) && (this.flightNum.equals(temp.flightNum)));
		}
		return false;
	}
}