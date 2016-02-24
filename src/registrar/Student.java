package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> coursesEnrolled;

    public Student(){
        coursesEnrolled = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return coursesEnrolled;
    }

    public boolean enrollIn(Course c){
        if(c.enrolled(this)) {
            coursesEnrolled.add(c);
            return true;
        }
        else {
            return false;
        }
    }

    public void drop(Course c){
        if (coursesEnrolled.contains(c)) {
            coursesEnrolled.remove(c);
        }
        c.dropStudent(this);
    }
}
