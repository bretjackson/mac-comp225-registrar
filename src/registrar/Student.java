package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    private Set<Course> courses;

    public Student(){
        courses = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return courses;
    }

    /**
     * Enroll student in specified course as long as the student isn't already enrolled in it
     * and there is space in the course
     * @param c
     * @return
     */
    public boolean enrollIn(Course c){
        if(c.isEnrolled(this)) { //what does the this mean?
            courses.add(c);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Drops a specific course
     * @param c
     */
    public void drop(Course c){
        if (courses.contains(c)) {
            courses.remove(c);
        }
        c.dropStudent(this);
    }
}
