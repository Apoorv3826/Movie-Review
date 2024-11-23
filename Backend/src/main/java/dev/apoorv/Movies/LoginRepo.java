package dev.apoorv.Movies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends MongoRepository<Users,String> {
    Optional<Users> findOneByUsernameAndPassword(String username, String password);

    Users findByUsername(String Username);
}
