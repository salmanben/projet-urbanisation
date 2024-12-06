package ma.ensa.urgence.Service;


import ma.ensa.urgence.DAO.DAO_Auth.InterfaceUser;
import ma.ensa.urgence.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IServiceUser {

    private final InterfaceUser UserRepository;


    @Autowired
    public UserService(InterfaceUser UserRepository) {
        this.UserRepository = UserRepository;

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return UserRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return UserRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }



}




