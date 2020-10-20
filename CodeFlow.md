Model 
-> Movie
-> Seat
-> Show
-> Booking Status
-> Booking
-> Screen
-> Theater
-> SeatLock

Locker
-> ISeatLocker
-> InMemorySeatLocker

Service
-> TheaterService
-> ShowService
-> MovieService
-> BookingService
-> PaymentService
-> SeatAvailabilityService

Controller
-> MovieController
-> TheaterController
-> ShowController
-> BookingController
-> PaymentsController

Controller Actions
------------------
localhost:8080/multiplex/register/
localhost:8080/multiplex/1/audi/add
localhost:8080/multiplex/show/add

localhost:8080/movie/register

localhost:8080/pay
localhost:8080/pay/retry

