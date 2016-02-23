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

    public Student(){
        enrolledIn = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return enrolledIn;
    }

    public boolean enroll(Course c){
        if(c.enrolledIn(this)) {
            enrolled.add(c);
            System.out.println("Student is succesfully registered in course "+c.getCourseNumber()+" "+c.getCourseTitle());
            return true;
        }
        else {
            System.out.println("Student is in the waitlist for course "+c.getCourseNumber()+" "+c.getCourseTitle());
            return false;
        }
    }

    public void drop(Course c){
        if (enrolledIn.contains(c)) {
            enrolledIn.remove(c);
        }
        c.dropStudent(this);
    }
}
