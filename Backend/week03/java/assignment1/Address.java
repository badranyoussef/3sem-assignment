package assignment1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Entity
@Table(name="address")
@Getter
@ToString
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "zip")
    private String zip;




    public Address(String street, String zip) {
        this.street = street;
        this.zip = zip;
    }
}
