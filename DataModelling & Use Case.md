## Data Modelling

Seat {
* SeatId <ToBe Derived by combination of {row,col,AudiID}>
* SeatType
}

Audi {
* Id
* AudiName
* SeatRow
* SeatColumns
}

Multiplex {
* Id
* TheaterName
* List<Audi>
}

Movie {
* MovieID
* MovieName
}

Show {
* ShowId
* Movie 
* Screen
* Multiplex
* StartTime
* Duration
}

Booking {
* BookingId
* Show
* List<BookedSeats>
* User 
* BookingStatus (Created, Confirmed, Expired, Cancelled)
}

SeatLock {
* LockId
* Seat
* ShowId
* TimeOut
* StartTime
* LockedBy (User)
}

Payment {
* BookingId
* PaymentStatus
* Retries
}

ShowSeatMatrix {
* Available (csv strings)
* Non-Available
}