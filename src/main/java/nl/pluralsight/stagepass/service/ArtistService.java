package nl.pluralsight.stagepass.service;

import nl.pluralsight.stagepass.model.Artist;
import nl.pluralsight.stagepass.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Optional<Artist> updateArtist(Long id, Artist updatedArtist) {
        return artistRepository.findById(id).map(existing -> {
            existing.setName(updatedArtist.getName());
            existing.setGenre(updatedArtist.getGenre());
            existing.setBio(updatedArtist.getBio());
            return artistRepository.save(existing);
        });
    }

    public boolean deleteArtist(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
