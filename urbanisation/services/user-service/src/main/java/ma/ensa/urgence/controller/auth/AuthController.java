package ma.ensa.urgence.controller.auth;

import lombok.RequiredArgsConstructor;
import ma.ensa.urgence.DAO.DAO_Auth.AuthService;
import ma.ensa.urgence.Model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.jwt.Jwt;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    // Déclarez le logger
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
      //  int id = jwt.getClaim("id"); // Retrieve the role from the JWT claim

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("email", userEmail);
        System.out.println("\n\nid=" +jwt.getClaim("id") + "\n\n");
        return ResponseEntity.ok(userInfo);
    }

}
