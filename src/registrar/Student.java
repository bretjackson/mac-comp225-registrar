package registrar;

import java.util.HashSet;
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
        return coursesEnrolled;
    }

    public void addCourse (Course c){
        coursesEnrolled.add(c);
    }

    public boolean enrollIn(Course c){
        //enrollIn returns true if this student was successfully added to the course c.
        if(c.enrollIn(this)) {
            return true;
        }
        return false;
    }

    public void drop(Course c){
        c.dropStudent(this);
    }

    public void removeCourse(Course c){
        coursesEnrolled.remove(c);
    }

    public String getName() {
        return name;
    }

}
