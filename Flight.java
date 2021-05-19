// Name: An Hoang
// Student ID: 501020760

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public class Flight extends Exceptions
{
	public enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};
	public static enum Type {SHORTHAUL, MEDIUMHAUL, LONGHAUL};
 
	String flightNum;
	String airline;
	String origin, dest;
	String departureTime;
	Status status;
	int flightDuration;
	Aircraft aircraft;
	protected int numPassengers;
	protected ArrayList<Passenger> manifest = new ArrayList<Passenger>(); // Array list of Passenger objects
	protected TreeMap<String, Passenger> seatMap = new TreeMap<String, Passenger>(); // maps a seat string to a Passenger object
	ArrayList<Passenger> passengersOnFlight = new ArrayList<Passenger>(); // Array list of Passenger objects in a day
	
	public Flight()
	{
		flightNum = "";
		airline = "";
		origin = "Toronto";
		dest = "";
		aircraft = null;
		departureTime = "";
		flightDuration = 0;
		numPassengers = 0;
		status = Status.DELAYED;
	}
	
	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		numPassengers = 0;
		status = Status.ONTIME;
		
	}
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getNumPassengers()
	{
		return numPassengers;
	}
	public void setNumPassengers(int numPassengers)
	{
		this.numPassengers = numPassengers;
	}

	public boolean seatsAvailable()
	{
		return this.aircraft.getTotalSeats() >= this.numPassengers;
	}

	public void cancelSeat()
	{
		if (this.getNumPassengers() > 0)
		{
			this.numPassengers --;
		}
	}

	public boolean reserveSeat()
	{
		if (seatsAvailable()) 
		{
			this.numPassengers ++;
			return true;
		}
		return false;
	}

	public Type getFlightType() // returns the type of flight
	{
		return Type.MEDIUMHAUL;
	}
	
	public String toString()
	{
		return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
	}

	public String PassengerToString(Passenger p)
	{
		return toString() + "\n Name:  " + p.getName() + "\t Passport: " + p.passportNum + "\t Seat: " + p.seatNum;
	}

	public boolean seatNotAvailable (int seatNumber)
	{
		boolean found = false;
		for (int i = 0; i <= passengersOnFlight.size() - 1; i++)
		{
			if (passengersOnFlight.get(i).seatNum == seatNumber)
			{
				found = true;
			}
		}
		return found;
	}

	public void addSeats(String seatNum, Passenger p)
	{
		seatMap.put(seatNum, p);
	}

	public void addPassenger(Passenger p)
	{
		manifest.add(p);
	}

	public void printSeat() //If a seat is occupied, print “XX “ 
	{
		String [][] seatLayout = aircraft.seatLayout();
		System.out.println();
		for (int i = 0; i <= seatLayout.length - 1; i++) 
		{
			for (int j = 0; j <= seatLayout[0].length - 1; j++) 
			{
				if (!seatMap.isEmpty() && seatMap != null)
				{
					boolean seatOccupied = false;
					for (Map.Entry<String, Passenger> seat : seatMap.entrySet())
					{
						if (seat.getKey().equals(seatLayout[i][j]))
							{
								seatOccupied = true;
							}
					}
					if (seatOccupied) 
					{
						System.out.print("XX ");
					}
					else 
					{
						System.out.print(seatLayout[i][j] + " ");
					}
					seatOccupied = false;
				}
				else 
				{
					System.out.print(seatLayout[i][j] + " ");
				}
			}
			if ((i % 2 == 0)) 
			{
				System.out.print("\n");
			}
			else 
			{
				System.out.println("\n");
			}
		}
		System.out.println("XX = Occupied" + "\t" + "+ = First Class");
	}

	public void reserveSeat(Passenger p, String seat)
	{
		for (Passenger person : manifest)
		{
			if (person.equals(p)) 
			{
				throw new DuplicatePassengerException("Duplicate Passenger " + p.getName() + " " + p.getPassport());
			}
		}
		for (Map.Entry<String, Passenger> seatNum : seatMap.entrySet())
			{
				if (seatNum.getKey().equals(seat))
				{
					throw new SeatOccupiedException("Seat "+ seat + " already occupied");
				}
			}
		addSeats(seat,p);
		addPassenger(p);
	}

	public void cancelSeat(Passenger p)
	{
		boolean found = false;
		for (Passenger person : manifest) 
		{
			if (person.equals(p)) 
			{
				found = true;
			}
		}
		if (!found)
		{
			throw new PassengerNotInManifestException("Passenger Not Found");
		}
		else 
		{
			manifest.remove(p);
			cancelSeat();
			seatMap.remove(p.getPassengerSeat());
		}
	}
	public void printPassengerManifest() // prints all the passengers on the flight
	{
		for (Passenger p : manifest)
		{
			System.out.println(p.toString());
		}
	}
}

