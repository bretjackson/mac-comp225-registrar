package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> enrolledIn;

    /**
     * Constructor
     */
    public Student(){
        enrolledIn = new HashSet<>();
    }

    /**
     * Set student's name
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets course for student
     * @return
     */
    public Set<Course> getCourses(){
        return enrolledIn;
    }

    /**
     * Adds student to class list
     * @param c
     * @return
     */
    public boolean enrollIn(Course c){
        if(c.enrollIn(this)) {
            enrolledIn.add(c);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Removes student from course
     * @param c
     */
    public void drop(Course c){
        if (enrolledIn.contains(c)) {
            enrolledIn.remove(c);
        }
        c.dropStudent(this);
    }
}
