package assignment01;

import lombok.Getter;

@Getter
public class NoteDTO {

    String note;
    String name;
    int age;

    public NoteDTO(String note, String name, int age) {
        this.note = note;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Note = " + note +
                ", name = " + name +
                ", age = " + age;
    }
}
