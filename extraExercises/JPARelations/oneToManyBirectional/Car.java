package oneToManyBirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "brand")
    String brand;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public void setCompany(Company company){
        if(company != null){
            this.company = company;
        }
    }

    public Car(String brand) {
        this.brand = brand;
    }
}