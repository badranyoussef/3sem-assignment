package assignment01;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQuery(name="Note.getNotePrId", query="SELECT n FROM Note n WHERE n.person.id = :person_id")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String note;
    private String createdBy;
    private LocalDate createdOn;

    @ManyToOne
    private Person person;

    public Note(String note, String createdBy, LocalDate createdOn) {
        this.note = note;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public String toString() {
             return "Note: " + note + " created by: " + createdBy + ", created on: " + createdOn + " -> customer name: " + person.getName();
    }
}
