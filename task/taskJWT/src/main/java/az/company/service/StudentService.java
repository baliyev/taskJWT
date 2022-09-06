package az.company.service;

import az.company.dto.StudentDto;
import az.company.entity.Address;
import az.company.entity.PhoneNumber;
import az.company.entity.Student;
import az.company.repository.StudentRepsitory;
import lombok.RequiredArgsConstructor;
import org.hibernate.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepsitory studentRepsitory;
    private final EntityManager entityManager;
    private final SessionFactory sessionFactory;
    @Override
    public List<Student> getStudents(){
        return studentRepsitory.findAll();
    }

    @Override
    public Student getStudent(String email){
        return studentRepsitory.findStudentByEmail(email).orElseThrow(()-> new RuntimeException("Student not found!"));
    }

    @Override
    public Student getStudent(Integer id){
        return  studentRepsitory.findStudentByStudentId(id).orElseThrow(()->new RuntimeException("Student not found!"));
    }
    @Override
    public void createStudent(StudentDto studentDto){
        studentRepsitory.findStudentByEmail(studentDto.getEmail()).ifPresentOrElse(_student->{
            throw new RuntimeException("This e-mail address is already in use.");
        },()->{
            Student student = new Student();
            student.setStudentName(studentDto.getName());
            student.setEmail(studentDto.getEmail());
            student.setRole(studentDto.getRole());
            student.setPassword(studentDto.getPassword());
            List<Address> addressList = new ArrayList<>();
            studentDto.getAddressList().forEach(addressDto->{
                Address address = new Address();
                address.setAddressDescription(addressDto.getAddressDescription());
                PhoneNumber phoneNumber = new PhoneNumber();
                phoneNumber.setPhoneNumber(addressDto.getPhoneNumber());
                address.setPhoneNumber(phoneNumber);
                address.getPhoneNumber().setAddressList(address);
                addressList.add(address);
            });
            student.setAddressList(addressList);
            student.getAddressList().forEach(address -> {
                address.setStudent(student);
            });

            studentRepsitory.save(student);
        });
    }
    @Override
    public List<Student> getStudentsByNativeQuery(){
        List<Student> list = entityManager.createNativeQuery("SELECT s.student_id," +
                " s.student_name, s.student_email," +
                " s.student_password, s.student_role, " +
                "a.address_id, a.address_description," +
                " p.phone_id, p.phone_number " +
                " FROM db_abbtask.abb_students s LEFT OUTER JOIN db_abbtask.abb_addresses a " +
                " ON a.student_id = s.student_id LEFT OUTER JOIN  db_abbtask.abb_phone_numbers p " +
                " ON p.phone_id = a.phone_id;", Student.class).getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public List<Student> getStudentsByHQL() {
        Session session = sessionFactory.openSession();
        var query= session.createQuery
                ("SELECT  s from Student s " +
                        "join fetch s.addressList ad " +
                        "join fetch ad.phoneNumber",Student.class);
        return query.list();
    }

    @Override
    public List<Student> getStudentByEntityGraph(String name){
        return studentRepsitory.findStudentByStudentName(name);
    }

}
