package dev.apoorv.Movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchListService {
    @Autowired
    private WatchlistRepository watchlistRepository;

    public List<WatchListItem> getWatchlistByUserId(String userId){
        return watchlistRepository.findByUserId(userId);
    }

    public void addToWatchlist(String userId, String movieId){
        WatchListItem watchListItem = new WatchListItem();
        watchListItem.setUserId(userId);
        watchListItem.setMovieId(movieId);
        watchlistRepository.save(watchListItem);
    }

    public void removeFromWatchlist(String userId, String movieId){
        watchlistRepository.deleteByUserIdAndMovieId(userId, movieId);
    }
}
