package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    private Set<Course> enrolledIn;

    public Student(){
        enrolledIn = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return enrolledIn;
    }

    public boolean enrollIn(Course c){
        if(c.enrollIn(this)) {
            enrolledIn.add(c);
            return true;
        }
        return false;
    }

    public void drop(Course c){
        enrolledIn.remove(c);
        c.dropStudent(this);
    }
}
