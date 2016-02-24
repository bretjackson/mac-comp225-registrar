package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {
    /**
     * name = student's name
     * courses = set of courses the student is taking
    */
    public String name;
    public Set<Course> courses;

    public Student(){ courses = new HashSet<>();}

    /**
     * sets the name of the student
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){ return courses; }

    /**
     * checks whether the student is already enrolled or not
     * @param c
     * @return
     */
    public boolean enrollIn(Course c){
        if(c.enrollIn(this)) {
            courses.add(c);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * drop the course from the course set
     * @param c
     */
    public void drop(Course c){
        if (courses.contains(c)) {
            courses.remove(c);
        }
        c.dropStudent(this);
    }
}
