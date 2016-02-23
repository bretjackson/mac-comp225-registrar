package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Refactored by sduong on 2/23/2016.
 * Students should know their registered courses.
 */
public class Student {

    public String studentName; //studentName of student
    public Set<Course> courses; //courses the student is enrolled in? rename...there is a function also called enrolledIn

    /**
     * constructor for Student...should probably set the studentName here in addition to remembering the set of courses
     */
    public Student(String s){
        this.courses = new HashSet<>();
        this.studentName = s;
    }


    /**
     * a getter method for getting the set of courses the student is enrolled in
     * @return courses
     */
    public Set<Course> getCourses(){
        return courses;
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean enrolledIn(Course c){
        if(c.checkStudentEnrollStatus(this)) {
            courses.add(c);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @param c
     */
    public void drop(Course c){
        if (courses.contains(c)) {
            courses.remove(c);
        }
        c.dropStudent(this);
    }
}
