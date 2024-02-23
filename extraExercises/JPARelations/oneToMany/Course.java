package oneToMany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "course_name")
    String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    @Setter
    @OneToMany
    private Set<Student> students = new HashSet<>();

//Hjælpe klase til at tilføje studerende til listen
    public void addStudent(Student student) {
        if(student != null)
        students.add(student);
    }

}
