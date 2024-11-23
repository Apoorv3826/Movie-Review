package dev.apoorv.Movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Login {

    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;
}

