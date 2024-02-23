package assignment03.model;

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
@Table(name = "semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "semester", cascade = CascadeType.PERSIST)
    private Set<Student> students = new HashSet<>();

    //Hjælpe klase til at tilføje studerende til listen
    public void addStudent(Student student) {
        if(student != null)
            students.add(student);
            student.setSemester(this);
    }

    public void addTeacher(Teacher teacher) {
        if(teacher != null){
            teachers.add(teacher);
            teacher.getSemesters().add(this);
        }
    }

    public Semester(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Semester: " + name + " - " +  description;
    }
}