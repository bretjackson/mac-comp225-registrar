package registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    private Set<Course> coursesEnrolled;

    public Student(){
        coursesEnrolled = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return Collections.unmodifiableSet(coursesEnrolled);
    }

    public boolean enrollIn(Course course){
        if(course.enrolled(this)) {
            coursesEnrolled.add(course);
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
