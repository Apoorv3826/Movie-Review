package dev.apoorv.Movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private WatchListService watchListService;

    public Iterable<Users> listAll() {
        return userRepository.findAll();
    }

    public void saveorUpdate(Users users) {
        userRepository.save(users);
    }

    public Users getUserbyId(String userid) {
        return userRepository.findById(userid).get();
    }


    public LoginResponse loginUser(Login login) {
        Users user1 = loginRepo.findByUsername(login.getUsername());
        if (user1 != null){
            String password = login.getPassword();
            String Password = user1.getPassword();

            if(password.equals(Password)){
                Optional<Users> users = loginRepo.findOneByUsernameAndPassword(login.getUsername(),login.getPassword());
                if (users.isPresent()){
                    String userId = users.get().getId();
                    return new LoginResponse(userId,"Login Success", true);
                }else {
                    return new LoginResponse(null,"Login Failed", false);
                }
            }else{
                return new LoginResponse(null,"Password not match", false);
            }
        }
        else{
            return new LoginResponse(null,"Username not exists", false);
        }
    }

    public void addToWatchlist(String userId, String movieId) {
        Users user = userRepository.findById(userId).orElse(null);

        if (user != null){
            List<String> watchlist = user.getWatchlist();
            if (watchlist == null){
                watchlist = new ArrayList<>();
            }

            watchlist.add(movieId);
            user.setWatchlist(watchlist);
            userRepository.save(user);
        }
    }

    public List<WatchListItem> getWatchlist(String userId) {
        return watchListService.getWatchlistByUserId(userId);
    }
}
