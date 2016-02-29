package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    private Set<Course> courseSchedule;

    public Student(){
        courseSchedule = new HashSet<>();
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return courseSchedule;
    }

    public boolean enrollInCourse(Course c){
        if(c.enrollStudent(this)) {
            courseSchedule.add(c);
            return true;
        }
        else {
            return false;
        }
    }

    public void drop(Course c) {
        courseSchedule.remove(c);
        c.dropStudent(this);
    }


}
