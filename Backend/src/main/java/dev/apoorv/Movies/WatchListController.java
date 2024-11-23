package dev.apoorv.Movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watchlist")
public class WatchListController {
    @Autowired
    private WatchListService watchListService;

    @GetMapping("{userId}")
    public List<WatchListItem> getWatchlistByUserId(@PathVariable String userId)
    {
        return watchListService.getWatchlistByUserId(userId);
    }

    @PostMapping("/add")
    public void addToWatchlist(@RequestParam String userId, @RequestParam String movieId){
        watchListService.addToWatchlist(userId,movieId);
    }

    @PostMapping("/remove")
    public void removeFromWatchlist(@RequestParam String userId, @RequestParam String movieId){
        watchListService.removeFromWatchlist(userId, movieId);
    }
}
