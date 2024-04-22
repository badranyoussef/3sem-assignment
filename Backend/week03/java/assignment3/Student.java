package assignment3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity
@Table(name="students")
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "age")
    int age;

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }


    @PrePersist
    @PreUpdate
    private void validateEmail() {
        if (!emailIsValid(email)) {
            throw new RuntimeException("Ugyldig e-mail adresse: " + email + ". Skal indeholde @ og ende på .dk eller .com");
        }
    }
    private boolean emailIsValid(String email) {
        //Validation
        return email != null && email.contains("@") && (email.contains(".dk") || email.contains(".com"));
    }

}
