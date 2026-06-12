package nl.pluralsight.stagepass.service;

import nl.pluralsight.stagepass.model.Venue;
import nl.pluralsight.stagepass.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Optional<Venue> getVenueById(Long id) {
        return venueRepository.findById(id);
    }

    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Optional<Venue> updateVenue(Long id, Venue updatedVenue) {
        return venueRepository.findById(id).map(existing -> {
            existing.setName(updatedVenue.getName());
            existing.setCity(updatedVenue.getCity());
            existing.setCapacity(updatedVenue.getCapacity());
            return venueRepository.save(existing);
        });
    }

    public boolean deleteVenue(Long id) {
        if (venueRepository.existsById(id)) {
            venueRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
