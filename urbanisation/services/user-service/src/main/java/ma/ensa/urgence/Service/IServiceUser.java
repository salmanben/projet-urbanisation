package ma.ensa.urgence.Service;

import ma.ensa.urgence.Model.User;

import java.util.List;
import java.util.Optional;

public interface IServiceUser {

    public Optional<User> findByEmail(String email);
    public Optional<User> findById(Integer id);
    public List<User> getAllUsers();


}
