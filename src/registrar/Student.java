package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> courses;

    public Student(){
        courses = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return courses;
    }

    public boolean enrollIn(Course c){
        if(c.enrollInClass(this)) {
            courses.add(c);
            return true;
        }
        else {
            return false;
        }
    }

    public void drop(Course c){
        if (courses.contains(c)) {
            courses.remove(c);
        }
        c.dropStudentFromWaitList(this);
    }
}
