// Name: An Hoang
// Student ID: 501020760

public class Exceptions 
{
	public class SeatOccupiedException extends RuntimeException
	{
		public SeatOccupiedException() 
		{
			super();
		}
		public SeatOccupiedException(String message)
		{
			super(message);
		}
	}

	public class PassengerNotInManifestException extends RuntimeException
	{
		public PassengerNotInManifestException()
		{ 
			super();
		}
		public PassengerNotInManifestException(String message) 
		{ 
			super(message);
		}
	}
	
	public class DuplicatePassengerException extends RuntimeException
	{
		public DuplicatePassengerException() 
		{
			super();
		}
		public DuplicatePassengerException(String message)
		{
			super(message);
		}
	}

    public class PassengerNotFound extends RuntimeException
    {
        public PassengerNotFound()
        {
            super();
        }
        public PassengerNotFound(String message)
        {
            super(message);
        }
    }

    public class PassengerDuplicate extends RuntimeException
    {
        public PassengerDuplicate() 
        {
            super();
        }
        public PassengerDuplicate(String message)
        {
          super(message);
        }
    }

    public class FlightNotFound extends RuntimeException
    {
        public FlightNotFound()
        {
            super();
        }
        public FlightNotFound(String message)
        {
            super(message);
        }
    }

    public class FlightIsFull extends RuntimeException
    {
        public FlightIsFull()
        {
            super();
        }
        public FlightIsFull(String message)
        {
            super(message);
        }
    }
}
