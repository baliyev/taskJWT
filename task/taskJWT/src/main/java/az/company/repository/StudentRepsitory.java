package az.company.repository;

import az.company.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepsitory extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByEmail(String email);
    @Query("SELECT  s from Student s join fetch s.addressList ad join fetch ad.phoneNumber where s.studentId = ?1")
    Optional<Student> findStudentByStudentId(Integer studentId);
    @EntityGraph(value = "students-with-address-phone")
    List<Student> findAll();
    @Query("SELECT  s from Student s join fetch s.addressList ad join fetch ad.phoneNumber")
    List<Student> findAllWithHQL();

    @Query(value = "SELECT s.student_id, " +
            "s.student_name, s.student_email," +
            "s.student_password, s.student_role, " +
            "a.address_id, a.address_description, " +
            "p.phone_id, p.phone_number FROM db_abbtask.abb_students s " +
            "LEFT OUTER JOIN db_abbtask.abb_addresses a  " +
            "ON a.student_id = s.student_id " +
            "LEFT OUTER JOIN  db_abbtask.abb_phone_numbers p " +
            " ON p.phone_id = a.phone_id;",nativeQuery = true)
    List<Student> findAllWithNativeQuery();
}
