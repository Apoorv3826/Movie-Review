package dev.apoorv.Movies;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WatchlistRepository extends MongoRepository<WatchListItem, String> {
    List<WatchListItem> findByUserId(String userId);

    void deleteByUserIdAndMovieId(String userId, String movieId);
}
