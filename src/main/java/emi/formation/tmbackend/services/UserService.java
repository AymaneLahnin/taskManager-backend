package emi.formation.tmbackend.services;


import emi.formation.tmbackend.dto.AuthResponse;
import emi.formation.tmbackend.dto.UserDTO;
import emi.formation.tmbackend.entities.User;
import emi.formation.tmbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  AuthResponse authResponse;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getUserById(Long id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        }
        else{
            return null;
        }
    }

    public User signUpService(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("A problem has occurred");
        }

        // Encrypt the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        User savedUser = userRepository.save(user);

        // Return the custom response with the user ID
        return savedUser;
    }


    public User signInService(UserDTO userSignUpDTO) {
        User user = userRepository.findByUsername(userSignUpDTO.getUsername());

        if (user != null && passwordEncoder.matches(userSignUpDTO.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }
    public User updateUser(User user,String newPassword) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }
        return null;
    }
}
