package ma.ensa.urgence.DAO.DAO_Auth;

import ma.ensa.urgence.Service.*;
import ma.ensa.urgence.Service.IServiceUser;
import ma.ensa.urgence.Service.UserService;
import ma.ensa.urgence.controller.auth.AuthResponse;
import ma.ensa.urgence.controller.auth.JwtUtil;
import ma.ensa.urgence.Model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private final IServiceUser UserService;
    private final PasswordEncoder passwordEncoder;
    private JwtUtil JwtUtil;


    public AuthService(ma.ensa.urgence.Service.UserService UserService, PasswordEncoder passwordEncoder,
                       JwtUtil JwtUtil) {
        this.UserService = UserService;
        this.passwordEncoder = passwordEncoder;
        this.JwtUtil = JwtUtil;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);



        public AuthResponse login(String email, String password) throws Exception {
            Object utilisateur = null;
            String nature = null;


            logger.info("Attempting to log in user with email: {}", email);

            // Vérification de l'étudiant
            User User = UserService.findByEmail(email).orElse(null);
            if (User != null) {
                utilisateur = User;
                nature = User.getRole().name();
                logger.info("User found: {}", User);
            } else {
                logger.info("No user found with email: {}", email);
            }



            // Si aucun utilisateur n'a été trouvé, lever une exception
            if (utilisateur == null) {
                logger.error("Utilisateur non trouvé pour l'email: {}", email);
                throw new Exception("Utilisateur non trouvé");
            }

            // Vérification du mot de passe
            String storedPassword;
                storedPassword = User.getMdp();

            if (!passwordEncoder.matches(password, storedPassword)) {
                logger.error("Mot de passe incorrect pour l'utilisateur: {}", email);
                throw new Exception("Mot de passe incorrect");
            }

            // Génération du token
            String token = JwtUtil.generateToken(User);
            logger.info("Login successful for user: {}", email);
            return new AuthResponse(utilisateur, nature, token);

        }


    }



