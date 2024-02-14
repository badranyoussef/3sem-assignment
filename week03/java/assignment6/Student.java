package assignment6;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

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
    @Column(name = "updated")
    LocalDateTime updated;
    @Column(name = "created")
    LocalDateTime created;

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }


    @PrePersist
        private void created() {
        created = LocalDateTime.now();
        updated = created;
    }
    @PreUpdate
    private void updated() {
        updated = LocalDateTime.now();
    }

}
