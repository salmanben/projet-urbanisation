package ma.ensa.urgence.controller.auth;

import ma.ensa.urgence.DAO.DAO_Auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    // Déclarez le logger
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse authResponse = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            // Créer un objet auth avec les données
            Map<String, Object> auth = new HashMap<>();
            auth.put("user", authResponse.getUser());
            auth.put("permissions", authResponse.getNature());
            auth.put("authToken", authResponse.getToken());

            return ResponseEntity.ok(auth); // Retourne l'objet auth
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }



    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getSubject();
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("email", userEmail);
        return ResponseEntity.ok(userInfo);

    }






}
