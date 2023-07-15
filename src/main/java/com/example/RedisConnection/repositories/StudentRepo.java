package com.example.RedisConnection.repositories;
import com.example.RedisConnection.modules.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentRepo {
    public static final String HASH_KEY = "Student";
    @Autowired
    private RedisTemplate template;

    public Student save(Student student){
        template.opsForHash().put(HASH_KEY,student.getId(),student);
        return student;
    }
    public List<Student> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }
    public Student findProductById(int id){
        System.out.println("DB Called");
        return (Student) template.opsForHash().get(HASH_KEY,id);
    }
    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "Student removed !!";
    }
}