package aprojectDS.payload.request;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class AddUserRequest {

    @Schema(required = true)
    private String username;

    @Schema(required = true)
    private String email;

    @Schema(example = "[ \"admin\"]", required = true)
    private List<String> role;

    @Schema(example = "parola123", required = true)
    private String password;

    @Schema(required = true)
    private String firstName;

    @Schema(required = true)
    private String lastName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRole() {
        return this.role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
