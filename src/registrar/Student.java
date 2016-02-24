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

    public boolean enrollIn(Course courseAdd){
        if(courseAdd.enrollIn(this)){
            //if the student was added to the enrolled list
            enrolledIn.add(courseAdd);
            return true;
        }
        return false;
    }

    public void drop(Course courseDrop){
        if (enrolledIn.contains(courseDrop)) {
            enrolledIn.remove(courseDrop);
            courseDrop.dropStudent(this);
        }
        //remove from waitlist
        else if(courseDrop.getWaitList().contains(this)){
            courseDrop.dropStudentFromWaitlist(this);
        }
    }
}
