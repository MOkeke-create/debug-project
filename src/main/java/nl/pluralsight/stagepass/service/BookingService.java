package nl.pluralsight.stagepass.service;

import nl.pluralsight.stagepass.exception.InsufficientSeatsException;
import nl.pluralsight.stagepass.model.Booking;
import nl.pluralsight.stagepass.model.Concert;
import nl.pluralsight.stagepass.repository.BookingRepository;
import nl.pluralsight.stagepass.repository.ConcertRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ConcertRepository concertRepository;

    public BookingService(BookingRepository bookingRepository, ConcertRepository concertRepository) {
        this.bookingRepository = bookingRepository;
        this.concertRepository = concertRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByConcert(Long concertId) {
        return bookingRepository.findAll();
    }

    @Transactional
    public Booking createBooking(Booking booking) {

        // Retrieve the concert
        Concert concert = concertRepository.findById(booking.getConcert().getId())
                .orElseThrow(() -> new RuntimeException("Concert not found"));

        // Number of tickets requested
        int ticketsRequested = booking.getNumberOfTickets();

        // Validate ticket quantity
        if (ticketsRequested <= 0) {
            throw new IllegalArgumentException("At least one ticket must be purchased.");
        }

        // Check seat availability
        if (concert.getAvailableSeats() < ticketsRequested) {
            throw new InsufficientSeatsException(
                    "Only " + concert.getAvailableSeats() + " seats are available."
            );
        }

        // Decrease available seats
        concert.setAvailableSeats(
                concert.getAvailableSeats() - ticketsRequested
        );

        // Save the updated concert
        concertRepository.save(concert);

        // Set booking details
        booking.setConcert(concert);
        booking.setBookingDate(LocalDate.now());

        // Calculate total price
        BigDecimal totalPrice = concert.getTicketPrice()
                .multiply(BigDecimal.valueOf(ticketsRequested));

        booking.setTotalPrice(totalPrice);

        // Save and return the booking
        return bookingRepository.save(booking);
    }

    public boolean cancelBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
