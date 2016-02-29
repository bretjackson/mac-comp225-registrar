package registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    private Set<Course> courses;

    /**
     * Constructor
     */
    public Student(){
        courses = new HashSet<>();
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
        //return Collections.unmodifiableSet(courses); //makes sure when we return set it cannot be changed
        return courses;
    }

    /**
     * Adds student to class list
     * @param course
     * @return
     */
    public boolean enrollIn(Course course){
        if(course.enrollIn(this)) {
            courses.add(course);
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
        if (courses.contains(c)) {
            courses.remove(c);
        }
        c.dropStudent(this);
    }
}
