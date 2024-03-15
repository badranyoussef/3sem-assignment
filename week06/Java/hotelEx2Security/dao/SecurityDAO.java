package hotelEx2Security.dao;

import hotelEx2Security.model.Role;
import hotelEx2Security.model.User;

public class SecurityDAO implements ISecurityDAO{

    @Override
    public User createUser(String username, String password) {
        return null;
    }

    @Override
    public Role createRole(String role) {
        return null;
    }

    @Override
    public User addRoleToUser(String username, String role) {
        return null;
    }
}
