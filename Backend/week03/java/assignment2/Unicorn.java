package assignment2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity
@Table(name="unicorn")
@Getter
@Setter
@ToString
public class Unicorn {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    //@Enumerated(EnumType.STRING)
    @Column(name = "power_strength")
    private int powerStrength;

    public Unicorn(String name, int age, int powerStrength) {
        this.name = name;
        this.age = age;
        this.powerStrength = powerStrength;
    }


    @PrePersist
    @PreUpdate
    private void validateEmail() {
        if (!numberValidator(powerStrength)) {
            throw new RuntimeException("Powerstrength skal være mellem 1-100");
        }
    }
    private boolean numberValidator(int powerStrength) {
        //Validation
        return powerStrength >= 1 && powerStrength <=100;
    }

    /*
    public enum PowerStrength{
        FIRE,
        FLY,
        SWIM
    }

     */
}
