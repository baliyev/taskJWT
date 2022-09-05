package az.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JsonIgnore
    private Student student;

    @JoinColumn(name = "phone_id", referencedColumnName = "phone_id")
    @OneToOne(optional = false)
    private PhoneNumber phoneNumber;
}
