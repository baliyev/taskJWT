package az.company.repository;

import az.company.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepsitory extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentByStudentId(Integer studentId);
    @EntityGraph(value = "students-with-address-phone", type = EntityGraph.EntityGraphType.LOAD)
    List<Student> findStudentByStudentName(String studentName);
}
