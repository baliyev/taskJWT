package az.company.controller;

import az.company.dto.StudentDto;
import az.company.entity.Student;
import az.company.mapper.SourceDestinationMapper;
import az.company.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IStudentService studentService;
    private final SourceDestinationMapper mapper;
    @GetMapping("/users/{id}")
    public ResponseEntity getStudent(@PathVariable Integer id){
        StudentDto studentDto = mapper.STUDENTEntity2Dto(studentService.getStudent(id));
        return ResponseEntity.ok().body(studentDto);
    }
    @GetMapping("/users")
    public ResponseEntity getStudents(){
        List<StudentDto> list = new ArrayList<>();
        studentService.getStudents().forEach(student ->list.add(mapper.STUDENTEntity2Dto(student)));
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/users/v2")
    public ResponseEntity getStudentsByNativeQuery(){
        List<StudentDto> list = new ArrayList<>();
        studentService.getStudentsByNativeQuery().forEach(student ->list.add(mapper.STUDENTEntity2Dto(student)));
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/users/v3")
    public ResponseEntity getStudentsByHQL(){
        List<StudentDto> list = new ArrayList<>();
        studentService.getStudentsByHQL().forEach(student ->list.add(mapper.STUDENTEntity2Dto(student)));
        return ResponseEntity.ok().body(list);
    }

}
