package az.company.service;

import az.company.dto.StudentDto;
import az.company.entity.Student;
import java.util.List;

public interface IStudentService {

    public List<Student> getStudents();
    public Student getStudent(String email);
    public Student getStudent(Integer id);
    public void createStudent(StudentDto studentDto);
    public List<Student> getStudentsByNativeQuery();
    public List<Student> getStudentsByHQL();
}
