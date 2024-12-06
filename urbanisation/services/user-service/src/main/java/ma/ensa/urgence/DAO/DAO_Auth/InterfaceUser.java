package ma.ensa.urgence.DAO.DAO_Auth;

import ma.ensa.urgence.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterfaceUser extends JpaRepository<User, Integer> {
   Optional<User> findByEmail(String email);
}
