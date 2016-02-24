package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    private Set<Course> enrolledCourses;

    public Student(){
        enrolledCourses = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public Set<Course> getCourses(){
        return enrolledCourses;
    }

    public boolean enrollIn(Course c){
        if(c.enrollStudent(this)) {
            enrolledCourses.add(c);
            return true;
        }
        return false;
    }

    public void drop(Course c){
        if (enrolledCourses.contains(c)) {
            enrolledCourses.remove(c);
        }
        c.dropStudent(this);
    }
}
