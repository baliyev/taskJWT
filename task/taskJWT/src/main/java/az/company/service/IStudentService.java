package az.company.service;

import az.company.entity.Student;
import java.util.List;

public interface IStudentService {

    public List<Student> getStudents();
    public Student getStudent(String email);
    public Student getStudent(Integer id);
    public Student getStudent(String email,String password);
    public boolean createStudent(Student student);



}
