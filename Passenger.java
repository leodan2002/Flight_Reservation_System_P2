// Name: An Hoang
// Student ID: 501020760

public class Passenger
{
    String flightNum;
    int passportNum;
    int seatNum;
    private String name;
    private String passport;
    private String seat;

    public Passenger()
    {
        this.flightNum = "";
        this.name = "";
        this.passportNum = 0;
        this.seatNum = 0;
    }
    
    public Passenger(String flightNum, String name, int passportNum, int seatNum)
    {
        this.flightNum = flightNum;
        this.name = name;               
        this.passportNum = passportNum; 
        this.seatNum = seatNum;
    }

    public Passenger(String flightNum, String name, String passport, String seat, String seatType)
    {
        this.flightNum = flightNum;
        this.name = name;
        this.passport = passport;
        this.seat = seat;
    }

    public String getName()
    {
        return name;
    }

    public String getPassport()
    {
        return this.passport;
    }
    
    public String getPassengerSeat()
    {
        return this.seat;
    }

    public String toString()
    {
        return name + " " + passport + " " + seat;
    }

    // This override method comapre a passenger is equal to another if they have same name and passport
    public boolean equals(Object other)
    {
        if (other instanceof Passenger)
        {
            Passenger temp = (Passenger) other;
            return (this.getName().equals(temp.getName()) && (this.passportNum == temp.passportNum));
        }
        return false;
    }
    
}