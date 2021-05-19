// Name: An Hoang
// Student ID: 501020760

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class FlightManager extends Exceptions
{
    TreeMap<String,Flight> flights = new TreeMap<>(); // maps the flightNum string to a Flight object
    
    String[] cities = {"Dallas", "New York", "London", "Paris", "Tokyo"};

    ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();
  
    Random random = new Random();


    public FlightManager()
    {
        TreeMap<String,Integer> flightTimes = new TreeMap<String,Integer>();
    	flightTimes.put("Dallas", 3);
  	    flightTimes.put("New_York", 1);
  	    flightTimes.put("London", 7);
  	    flightTimes.put("Paris", 8);
  	    flightTimes.put("Tokyo", 16);

        airplanes.add(new Aircraft(44, "Boeing 737"));
  	    airplanes.add(new Aircraft(20, "Bombardier 5000"));
  	    airplanes.add(new Aircraft(84, "Dash-8 100"));
  	    airplanes.add(new Aircraft(80, "Airbus 320"));
  	    airplanes.add(new Aircraft(100, 12, "Boeing 747"));

        try
        {
            Scanner scanner = new Scanner(new File("flights.txt"));
            int count = 0;
            while (scanner.hasNextLine())
            {
                String airline = scanner.next();
                String dest = scanner.next();
                String departureTime = scanner.next();
                int largestSeatNum = scanner.nextInt();
                String flightNum = generateFlightNumber(airline);

                Aircraft airplane = airplanes.get(0);
                for (Aircraft a : airplanes) 
                {
                    if (a.getTotalSeats() >= largestSeatNum) 
                    {
                        airplane = a;
                        break;
                    }
                }

                if (flightTimes.get(dest) <= 8)
                {
                    Flight flight = new Flight(flightNum, airline, dest, departureTime, flightTimes.get(dest), airplane);
                    flights.put(flightNum,flight);
                    
                }
                else 
                {
                    LongHaulFlight flight = new LongHaulFlight(flightNum, airline, dest, departureTime, flightTimes.get(dest), airplane);
                    flights.put(flightNum,flight);
                }

                count ++;
                if (scanner.hasNextLine()) 
                {
                    scanner.nextLine();
                }
            }
        } 
        catch (NullPointerException | FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    } 
    private String generateFlightNumber(String airline)
    { 
        String flightNum = "";
  	    for (int i = 0; i <= airline.length() - 1; i++)
        {
            if (Character.isUpperCase(airline.charAt(i)))
            {
                flightNum = flightNum.concat(String.valueOf(airline.charAt(i)));
            }
        }
  	    int flight = random.nextInt(200) + 101;
  	    flightNum = flightNum.concat(String.valueOf(flight));
        return flightNum;
    }

    public boolean seatsAvailable(String flightNum)
    {
        if (!flights.get(flightNum).seatsAvailable()) throw new FlightIsFull("Flight "+ flightNum + " Is Full");
        {
            return true;
        }
    }

    public void printAllFlights()
    {
  	    for (Map.Entry<String,Flight> f : flights.entrySet())
        {
            System.out.println(f.getValue().toString());
        }
    }

    // reserve a seat on a flight
    public Reservation reserveSeatOnFlight(String flightNum, String flightInfo)
    {
        if (seatsAvailable(flightNum))
        {
            Flight flight = flights.get(flightNum);
            String seatType;
            Reservation resInfo = new Reservation(flight.flightNum, flightInfo);
            if (resInfo.firstClass)
            {
                seatType = LongHaulFlight.firstClass;
            }
            else
            {
                seatType = LongHaulFlight.economy;
            }
            if (flightInfo.equals(LongHaulFlight.firstClass)) 
            {
                resInfo.setFirstClass();
            }
            if (flight instanceof LongHaulFlight && resInfo.firstClass)
            {
                LongHaulFlight longHaulFlight = new LongHaulFlight(flight.getFlightNum(), flight.getAirline(), flight.getDest(), flight.getDepartureTime(), flight.getFlightDuration(), flight.aircraft);
                if (longHaulFlight.reserveSeat(seatType))
                {
                    Reservation firstClassTicket = new Reservation(flight.getFlightNum(),seatType);
                    firstClassTicket.setFirstClass();
                    return firstClassTicket;
                } 
                else 
                {
                    throw new FlightIsFull("Flight " + flightNum + " Is Full"); 
                }
            } 
            else if (flights.get(flightNum).reserveSeat())
            {
                Reservation res = new Reservation(flightNum, flightInfo);
                return res;
            }
            if (flightInfo.equals(LongHaulFlight.firstClass) && !(flight instanceof LongHaulFlight))
            {
                throw new FlightNotFound("Flight " + flightNum + ": Not A Long Haul Flight"); 
            }
        }
        throw new FlightIsFull("Flight " + flightNum + " Is Full");
    }

    // reserve a seat with flightnum, passenger name, passport,and seat number
    public Reservation reserveSeatOnFlight(String flightNum, String name, String passport, String seatNum ,String info)
    {
        if (seatsAvailable(flightNum)) 
        {
            if (flights.get(flightNum).reserveSeat())
            {
                String flightType;
                if (seatNum.contains("+"))
                {
                    flightType = "FCL";
                }
                else
                {
                    flightType = "ECO";
                }
                Passenger p = new Passenger(flightNum, name, passport, seatNum, flightType);
                flights.get(flightNum).reserveSeat(p, seatNum);
                Reservation res = new Reservation(flightNum, name, passport, seatNum, info);
                return res;
            }
        }
        if (name == null || passport == null || seatNum == null) 
        {
            throw new PassengerNotFound("Passenger Cannot Be Added");
        }
        throw new FlightIsFull("Flight " + flightNum + " Is Full");
    }

    // checking to see if flightnum is in list
    public boolean flightSearching(String flightNum)
    {
        for (Map.Entry<String, Flight> f : flights.entrySet())
        {
            if (f.getKey().equals(flightNum))
            {
                return true;
            }
        }
        throw new FlightNotFound("Flight " + flightNum + " Not Found");
    }

    public void cancelReservation(Reservation res)
    {
  	    if (res == null) 
        {
            throw new FlightNotFound("Flight Not Found");
        }
  	    flights.get(res.flightNum).cancelSeat();
    }

    // checking if the passenger is duplicated, by comparing name and passport to the existing passenger info
    public void passengersComparator(String name, int passport, String reservationName, int reservationPassport)
    {
        if (reservationName == null)
        {
            return; 
        }
        if (name.equals(reservationName))
        {   
            if (passport == reservationPassport)
            {    
                throw new FlightManager.PassengerDuplicate("This seat is booked by a passenger");
            }
        }
        else if (passport == reservationPassport) 
        {
            throw new PassengerDuplicate("This passport is invalid because it is duplicated to someone else");
        }
    }
    
    public int findFlightIndex(String flightNum)
    {
        if (seatsAvailable(flightNum)) 
        {
            for (int i = 0; i <= flights.size() - 1; i++)
            {
                if (flights.get(i).flightNum.equalsIgnoreCase(flightNum)) 
                {
                    return i;
                }
            }
        }
        throw new FlightNotFound("Flight " + flightNum + " Not Found");
    }
}