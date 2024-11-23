package dev.apoorv.Movies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.apoorv.Movies.Users;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")

public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody Users users){
        users.setWatchlist(new ArrayList<>());
        userServices.saveorUpdate(users);
        return users.getId();
    }

    @GetMapping(value = "/getAll")
    public Iterable<Users>getUsers(){
     return userServices.listAll();
    }

    @RequestMapping("/search/{id}")
    private Users getUsers(@PathVariable(name = "id")String userid){
        return userServices.getUserbyId(userid);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login){
        LoginResponse loginResponse = userServices.loginUser(login);
        System.out.println("User ID from login: " + loginResponse.getId());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(value = "/addToWatchlist/{userId}/{movieId}")
    public ResponseEntity<?> addToWatchlist(@PathVariable String userId, @PathVariable String movieId) {
        userServices.addToWatchlist(userId, movieId);
        return ResponseEntity.ok("Movie added to watchlist successfully");
    }

    @GetMapping(value = "/getWatchlist/{userId}")
    public ResponseEntity<?> getWatchlist(@PathVariable String userId) {
        List<WatchListItem> watchlist = userServices.getWatchlist(userId);
        return ResponseEntity.ok(watchlist);
    }
}
