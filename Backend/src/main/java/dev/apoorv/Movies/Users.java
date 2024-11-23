package dev.apoorv.Movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    @Id
    private String id;
    private String username;
    private String password;
    private List<String> watchlist = new ArrayList<>();

}
