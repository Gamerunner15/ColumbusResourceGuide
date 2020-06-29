package Projects.Kolton.Serverside_API.security.repository;

import java.util.Optional;

import Projects.Kolton.Serverside_API.security.model.User;

public interface UserRepository {

    Optional<User> findByUsername(String username);

}
