package assignment02.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @Column(name = "id", length = 25, nullable = false)
    private String id;

    @Temporal(TemporalType.DATE)
    @Column(name = "employment_date")
    private Date employmentDate;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "surname", length = 255, nullable = false)
    private String surname;

    @Column(name = "salary", precision = 38, scale = 2, nullable = false)
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private WasteTruck truck;

    public Driver(String name, String surname, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    @PrePersist
    private void prePersist() {
        if (this.employmentDate == null) {
            this.employmentDate = new Date();
        }

        if (!validateDriverId(createDriverId())) {
            // Kaster en RuntimeException hvis ID ikke er gyldig
            throw new RuntimeException("Driver ID is not valid and data will not be persisted");
        }
        System.out.println("ID is valid and data is persisted");
    }


    private Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }

    private String createDriverId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        String date = dateFormat.format(this.employmentDate);

        String nameParts = this.name.substring(0, 1) + this.surname.substring(0, 1);

        Random random = new Random();
        int randomNumber = 100 + random.nextInt(900); // 100-999 Da selve random.nextInt(900) giver et tal mellem 0 og 899. Da vi plusser dette med 100 vil resultatet bliver mellem 100 og 999
        String numberPart = String.valueOf(randomNumber);

        String lastLetterPart = this.surname.substring(this.surname.length() - 1);

        this.id = date + "-" + nameParts.toUpperCase() + "-" + numberPart + lastLetterPart.toUpperCase();
        return this.id;
    }

    @Override
    public String toString() {
        return "Name: " + name
                + " " + surname
                + " - Date of employment: " + employmentDate
                + " - Salary: " + salary;
    }
}
