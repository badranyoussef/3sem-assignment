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
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "course")
    String courseName;

    @ManyToMany(mappedBy = "students")
    Set<Course> courses = new HashSet<>();

    public Student(String courseName) {

        this.courseName = courseName;
    }

    public void addCourse(Course course){
        if(course != null){
            courses.add(course);
        }
    }

}