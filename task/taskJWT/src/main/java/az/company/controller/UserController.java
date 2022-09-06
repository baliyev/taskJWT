package az.company.controller;

import az.company.entity.Student;
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
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IStudentService studentService;
    private final EntityManager entityManager;
    @GetMapping("/users")
    public ResponseEntity getStudents(){
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getStudent(@PathVariable Integer id){
        return ResponseEntity.ok().body(studentService.getStudent(id));
    }

    @GetMapping("/users/v1")
    public ResponseEntity getStudentsByNativeQuery(){
        return ResponseEntity.ok().body(studentService.getStudentsByNativeQuery());
    }

    @GetMapping("/users/v2")
    public ResponseEntity getStudentsByHQL(){
        return ResponseEntity.ok().body(studentService.getStudentsByHQL());
    }

    @GetMapping("/users/v3/{name}")
    public ResponseEntity getStudentsByEntityGraph(@PathVariable("name") String name){
        return ResponseEntity.ok().body(studentService.getStudentByEntityGraph(name));
    }
}
