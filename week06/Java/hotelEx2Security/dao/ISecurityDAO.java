package hotelEx2Security.dao;

import hotelEx2Security.model.Role;
import hotelEx2Security.model.User;

public interface ISecurityDAO {

    User createUser(String username, String password);

    Role createRole(String role);

    User addRoleToUser(String username, String role);

}
