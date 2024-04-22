package hotelEx2Security.dto;

import hotelEx2Security.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private Set<String> roles;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getUserPassword();
        this.roles = user.getRolesAsStrings();
    }
    public UserDTO(String username, Set<String> rolesSet) {
        this.username = username;
        this.roles = rolesSet;
    }
}