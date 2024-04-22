package hotelEx2Security.daos;

import hotelEx2Security.model.Role;
import hotelEx2Security.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDAO implements ISecurityDAO{
    private static EntityManagerFactory emf;
    private static UserDAO instance;
    public UserDAO(EntityManagerFactory _emf){
        this.emf = _emf;
    }

    public static UserDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new UserDAO(emf);
        }
        return instance;
    }

    @Override
    public User createUser(String username, String password) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = new User(username, password);

        Role role = em.find(Role.class, "user");
        if (role == null){
            role = new Role("user");
            em.persist(role);
        }

        user.addRole(role);
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

//    public User createUser(User user) {
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(user);
//        em.getTransaction().commit();
//        em.close();
//        return user;
//    }
//
    @Override
    public User createUser(String username, String password, Set<String> roles) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = new User(username, password);

        Set<Role> rolesSet = roles.stream().map(s -> new Role(s)).collect(Collectors.toSet());
        for (Role role : rolesSet) {
            user.addRole(role);
        }

        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public User verifyUser(String username, String password){
        EntityManager em = emf.createEntityManager();
            User user = em.find(User.class, username);
            if(user == null) throw new EntityNotFoundException("No user found");
            if(!user.verifyPassword(password)) throw new EntityNotFoundException("wrong password");
            return user;
    }

    @Override
    public Role createRole(String role) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Role newRole = new Role(role);
            em.persist(newRole);
            em.getTransaction().commit();
            return newRole;
        }
    }

    @Override
    public User addRoleToUser(String username, String role) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, username);
        Role _role =  em.find(Role.class, role);
        if(user != null){
            user.addRole(_role);
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }else{
            throw new EntityNotFoundException("User not found. Therefore no role added");
        }
        return user;
    }
}
