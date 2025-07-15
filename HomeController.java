 // Change the package name as needed
 package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "*") // Allow all origin s
@RestController
public class HomeController {
    
    static List<Student> result=null;
    @GetMapping("/hello") //api1
    public String home() {
        return "Hello from Spring Boot!";
    }
    @GetMapping("/api/students") //api2
    public  List<Student> getStudents() {
        return result;
    }
    @PostMapping("/api/students")
    public ResponseEntity<Student> createStudentData(@RequestBody Student s1){
    System.out.println(s1);
    List <Student> l1 = createList();
    l1.add(s1);
    return ResponseEntity.ok()
        .header("Cache-Control","no-store")
        .body(s1);

    }
    /*public ResponseEntity<Student> deleteStudentData(@RequestBody Student s1){
    // write this st. just before func defn end System.out.println(s1);
    List <Student> l1 = deleteList();
    return ResponseEntity.ok()
        .header("Cache-Control","no-store")
        .body(s1);
    System.out.println(s1);
    }*/
    public static List<Student>  createList()
    {
        result = new ArrayList<>();
        result.add(new Student("Jay", "darvekar", "example@gmail.com", "pune", "9881152524", "30/6/25"));
        return result;   
    }
    public static List<Student> deleteList(List<Student> result)
    {   
        result.clear();
        return result;
    }
    @DeleteMapping("/api/students/{email_id}")
    public ResponseEntity<Void> delete(@PathVariable String email_id) {
    for (Student s : result) {
        if (s.getEmail_id().equalsIgnoreCase(email_id)) {
            result.remove(s);
            return ResponseEntity.noContent().build(); 
        }
    }
    return ResponseEntity.notFound().build();
}
    @PutMapping("/api/students/{email_id}")
    public ResponseEntity<Student> updateStudentData(@PathVariable String email_id, @RequestBody Student s1){
    for(Student s : result)
    {   
        if(s.getEmail_id().equalsIgnoreCase(email_id)){
            s.setFname(s1.getFname());
            s.setLname(s1.getLname());
            s.setAddress(s1.getAddress());
            s.setMob_no(s1.getMob_no());
            s.setDate(s1.getDate());
            return ResponseEntity.ok(s);
        }
       
    }
     return ResponseEntity.notFound().build();
    }
}