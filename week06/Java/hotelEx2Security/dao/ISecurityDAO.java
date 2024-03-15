package hotelEx2Security.dao;

import hotelEx2Security.model.Role;
import hotelEx2Security.model.User;

import java.util.Set;

public interface ISecurityDAO {

    User createUser(String username, String password);

    User createUser(String username, String password, Set<String> roles);

    Role createRole(String role);

    User addRoleToUser(String username, String role);

}
