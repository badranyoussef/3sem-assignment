package manyToMany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "student_name")
    String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    Set<Student> students = new HashSet<>();

    public Course(String name) {
        this.name = name;
    }

    public void addStudent(Student s1) {
        if(s1 != null){
            students.add(s1);

        }
    }
}