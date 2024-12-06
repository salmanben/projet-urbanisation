package ma.ensa.urgence.controller.auth;

public class AuthResponse {
    private Object user; // L'utilisateur trouvé (Etudiant, Prof ou Directeur)
    private String nature; // Nature de l'utilisateur
    private String token; // Token JWT

    // Constructeur
    public AuthResponse(Object user, String nature, String token) {
        this.user = user;
        this.nature = nature;
        this.token = token;
    }

    // Getters et Setters
    public Object getUser() { return user; }
    public String getNature() { return nature; }
    public String getToken() { return token; }
}

