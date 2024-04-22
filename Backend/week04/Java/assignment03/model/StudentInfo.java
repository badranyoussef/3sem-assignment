package assignment03.model;

import lombok.Getter;

@Getter
public class StudentInfo {
    private String fullName;
    private int studentId;
    private String thisSemesterName;
    private String thisSemesterDescription;

    public StudentInfo(String fullName, int studentId, String thisSemesterName, String thisSemesterDescription) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.thisSemesterName = thisSemesterName;
        this.thisSemesterDescription = thisSemesterDescription;
    }

    @Override
    public String toString() {
        return "StudentInfo: " + '\n'+
                "FullName: " + fullName + '\n'+
                "Student ID: " + studentId +  '\n'+
                "Name of semester: " + thisSemesterName +  '\n'+
                "Description: " + thisSemesterDescription;
    }
}
