package registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;// Make variables private (defensive programming)
    private Set<Course> courses; // make it private and change to courses

    // String cannot be changed after it is created. No method can change its content
    //

    public Student(){
        courses = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    // returning courses directly is as bad as making courses public, because we can change the set by using getCourses.
    // Should return an unmodifiable set
    public Set<Course> getCourses(){
        return Collections.unmodifiableSet(courses);
    }

    public boolean enrollIn(Course course){
        if(course.enroll(this)) {
            courses.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    public void drop(Course course){
        if (courses.contains(course)) {
            courses.remove(course);
        }
        course.dropStudent(this);
    }
}
