package az.company.entity;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "abb_addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    private String addressDescription;

    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.LAZY)
    private PhoneNumber phoneNumber;

}
