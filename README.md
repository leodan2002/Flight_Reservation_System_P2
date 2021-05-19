# Flight_Reservation_System_P2
school project CPS209

Flight Reservation System 2:

1) 
- There is a file I/O and exception handling for reading the flights.txt file in FlightManager. 
- I have populated the flights map in FlightManager.

2) Exceptions:
- I have created customized exceptions: DuplicatePassengerException, PassengerNotInManifestException, SeatOccupiedException, 
PassengerNotFound, PassengerDuplicate, FlightNotFound, FlightIsFull.

3) Commands: 
- res flightnum name passport seatnum: res UA294 An AB123 1A
- cancel flightnum name passport seatnum: cancel UA294 An AB123 1A (unlike the video, I also add seatnum in the command)
- pasman flightnum: pasman UA294
- all three commands work fine.

4) Seat Layout
- seats flightnum: seats UA294
- this one works fine.

5) 
- Mapping seats and updating of seats occupied("XX") work fine.

6) Use of a Map in class FlightManager instead of array list in FlightManager
- Yes, I did that. 

7) Bonuses
- No bonus a or b. 
