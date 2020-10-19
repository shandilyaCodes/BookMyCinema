## Problem Statement -- Movie Ticket Booking Application

Implement a Movie ticket booking system. Find the below details related to the problem

1. A Theater has screens which runs the "shows" for multiple movies. Each show has a particular movie, start time, 
   duration and is played in a particular screen in the theater. Each screen has an arrangement of seats that can be 
   booked by the users.
2. Assumed that all the users are logged in authenticated to the system
3. Once a user selects a particular Show to book tickets for, a UserBookingSession starts. Within this UserBookingSession
   a User will be able to get the available seats for the show and select the seats he wishes to book. It is a 'good to
   have' for the application to have limits on the number of Seats a user can book in a ticket
4. Once the user has selected a group of seats, these seats should become "temporarily unavailable" to all the other users
5. The user then proceeds to make payment which can either be SUCCESS or FAILURE
6. If the payment is FAILED, user can retry payment for a maximum number of times. Beyond maximum retries, the seats are
   made available. 
7. If the payment succeeds, ticket or booking confirmation is generated and made available to the user. The 'UserBooking
   Session' is closed and the seats are made "Permanently Available"
8. A User can also explicitly close the UserBookingSession after selecting seats and before making payment. In this case
   the seats selected are made "Available" once again
   

## Use Case Flows

The implementation is supposed to demonstrate the below use case scenarios. There are two users U1 & U2 in the application
The Users can retrieve all the available shows and select one show. 

### CASE 1 

1. Say U1 & U2 select the same show
2. U1 requests for and gets all the available seats for this show
3. U1 selected a group of seats and proceeds to pay
4. U2 requests for and gets all the available seats for this show. U2 should not see the seats selected by U1 as AVAILABLE
5. Payment succeeds for U1
6. U1 receives tickets with the seats confirmed

### CASE 2

1. Say U1 & U2 select the same show
2. U1 & U2 requests for and gets all available seats for this show
3. U1 selects groups of seats
4. U1 proceeds to pay
5. U2 requests for and gets all the available seats for this show. U2 should not see the seats selected by U1 as available
6. Payment fails for U1. Assume maximum payment retries for this demo to be zero. Also show in another scenario where U1
   UserBookingSession is explicitly closed by U1 before payment is completed
7. U2 again requests for and gets to see all the available seats for this show. U2 should now see the seats previously
   selected by U1 as available
   
### CASE 3

1. Say U1 & U2 select the same show
2. U1 & U2 requests for and get all available seats for this show
3. U1 selects a group of seats and proceeds to pay
4. U2 selects overlapping seats and proceeds to pay. U2 should be notified that "One or more of the selected seats" are
   not available at this moment.
   
### Bonus
-> Have a configurable timeout for a UserBookingSession

### Expectations

1. Create the sample data yourself
2. Code should be demonstrable
3. Code should follow SOLID principles
4. Code should handle edge cases and follow gracefully


### Guidelines

1. DB not expected, you may store in memory
2. First expected then bonus



















   