// Name: An Hoang
// Student ID: 501020760

import java.util.ArrayList;
import java.util.Scanner;

public class FlightReservationSystem extends Exceptions
{
	public static void main(String[] args) throws Flight.PassengerNotInManifestException, FlightManager.FlightNotFound, FlightManager.FlightIsFull,	FlightManager.PassengerDuplicate, FlightManager.PassengerNotFound,
												Flight.DuplicatePassengerException, Flight.SeatOccupiedException
	{
		FlightManager manager = new FlightManager();

		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			Scanner commandLine = new Scanner(inputLine);

			String action = commandLine.next();

			if (action == null || action.equals("")) continue;

			if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			

			else if (action.equalsIgnoreCase("LIST"))
			{
				manager.printAllFlights(); 
			}

			// this method helps reserving a flight for passenger with name, passport and specified seat
			else if (action.equalsIgnoreCase("RES"))
			{
				if (commandLine.hasNext())
				{
					String flightNum = commandLine.next().toUpperCase();
					String name = "";
					String passport = "";
					String seatNum = "";
					if (commandLine.hasNext()) 
					{
						name = commandLine.next();
					}
					if (commandLine.hasNext()) 
					{
						passport = commandLine.next();
					}
					if (commandLine.hasNext()) 
					{
						seatNum = commandLine.next();
					}
					try
					{
						manager.flightSearching(flightNum);
						String info = manager.flights.get(flightNum).toString();
						Reservation res = manager.reserveSeatOnFlight(flightNum, name, passport, seatNum, info);
						myReservations.add(res);
						res.print();
					}
					catch (Flight.PassengerNotInManifestException | Flight.SeatOccupiedException | FlightManager.FlightNotFound | Flight.DuplicatePassengerException | FlightManager.FlightIsFull e)
					{
						System.out.println(e.getMessage());
					}
				}
			}

			// this method helps printing out the seats on this flight 
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = null;

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
					flightNum = flightNum.toUpperCase();
					try
					{
						manager.flightSearching(flightNum);
						manager.seatsAvailable(flightNum);
						manager.flights.get(flightNum).printSeat();
					}
					catch (FlightManager.FlightNotFound e)
					{
						System.out.println(e.getMessage());
					}
					catch (FlightManager.FlightIsFull e)
					{
						manager.flights.get(flightNum).printSeat();
						System.out.println(e.getMessage());
					}	
				}
			}
			
			// this method helps cenceling a reservation on flight for passenger with name, passport, and seatNum
			else if (action.equalsIgnoreCase("CANCEL"))
			{
				if (commandLine.hasNext()){
					String flightNum = commandLine.next().toUpperCase();
					String name = "";
					String passport = "";
					String seatNum = "";
					if (commandLine.hasNext()) 
					{
						name = commandLine.next().toUpperCase();
					}
					if (commandLine.hasNext()) 
					{
						passport = commandLine.next().toUpperCase();
					}
					if (commandLine.hasNext()) 
					{
						seatNum = commandLine.next().toUpperCase();
					}
					try
					{
						manager.flightSearching(flightNum);
						Flight f = manager.flights.get(flightNum);
						Passenger p = f.seatMap.get(seatNum);
						f.cancelSeat(p);
						for (int i = 0; i <= myReservations.size() - 1; i++)
						{
							if (myReservations.get(i).flightInfo.equals(f.toString())) 
							{
								Reservation res = myReservations.get(i);
								myReservations.remove(res);
							}
						}
					}
					catch (Flight.PassengerNotInManifestException | Flight.SeatOccupiedException | FlightManager.FlightNotFound | Flight.DuplicatePassengerException | FlightManager.FlightIsFull e)
					{
						System.out.println(e.getMessage());
					}
				}
			}

			// this method helps printing out information about all passengers of a specific flight
			else if (action.equalsIgnoreCase("PASMAN"))
			{
				if (commandLine.hasNext()) 
				{
					String flightNum = commandLine.next().toUpperCase();
					manager.flights.get(flightNum).printPassengerManifest();
				}
			}

			// this method helps printing all reservations
			else if (action.equalsIgnoreCase("MYRES"))
			{
				for (Reservation res : myReservations)
				{
					res.print();
				}
			}
			System.out.print("\n>");
		}
	}	
}