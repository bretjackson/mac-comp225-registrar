package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    public Set<Course> enrolledIn;

    public String getName() {
        return name;
    }

    public Set<Course> getEnrolledIn() {
        return enrolledIn;
    }

    public Student(){
        enrolledIn = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEnrolledIn(Set<Course> enrolledIn) {
        this.enrolledIn = enrolledIn;
    }

    public Set<Course> getCourses(){
        return enrolledIn;
    }

    public boolean enrollIn(Course course){
        if(course.enrollIn(this)) {
            enrolledIn.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    public void drop(Course course){
        if (enrolledIn.contains(course)) {
            enrolledIn.remove(course);
        }
        course.dropStudent(this);
    }
}
