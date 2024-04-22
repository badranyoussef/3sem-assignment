package oneToOne;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToOne
    private Teacher teacher;


    public void setTeacher(Teacher teacher){
        if(teacher != null){
            this.teacher = teacher;
        }
        if(teacher.getCourse() != this){
            teacher.setCourse(this);
        }
    }


}
