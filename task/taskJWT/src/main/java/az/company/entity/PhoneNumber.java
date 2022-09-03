package az.company.entity;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "abb_phone_numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phoneId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Address address;

}
