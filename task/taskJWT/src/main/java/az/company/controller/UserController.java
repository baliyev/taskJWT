package az.company.controller;

import az.company.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IStudentService studentService;

    @GetMapping("/users")
    public ResponseEntity getStudents(){
        String s = "";
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getStudent(@PathVariable Integer id){
        String s = "";
        return ResponseEntity.ok().body(studentService.getStudent(id));
    }

}
