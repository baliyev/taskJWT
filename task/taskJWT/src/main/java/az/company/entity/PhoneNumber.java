package az.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "abb_phone_numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Integer phoneId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "phoneNumber")
    @JsonIgnore
    private Address addressList;
}
