package az.company.service;

import az.company.dto.StudentDto;
import az.company.entity.Address;
import az.company.entity.PhoneNumber;
import az.company.entity.Student;
import az.company.mapper.SourceDestinationMapper;
import az.company.mapper.SourceDestinationMapperImpl;
import az.company.repository.StudentRepsitory;
import lombok.RequiredArgsConstructor;
import org.hibernate.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
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
    private final SourceDestinationMapper mapper;

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
            Student _student = mapper.STUDENTDto2Entity(studentDto);
            _student.getAddressList().forEach(address -> {
                address.setStudent(_student);
            });
            studentRepsitory.save(_student);
        });
    }
    @Override
    public List<Student> getStudentsByNativeQuery(){
       return studentRepsitory.findAllWithNativeQuery();
    }

    @Override
    public List<Student> getStudentsByHQL() {
        return studentRepsitory.findAllWithHQL();
    }


}
