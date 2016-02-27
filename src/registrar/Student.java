package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {
    //Made these private, other classes shouldn't be able to change them
    /**
     * The name of the student
     */
    private String name;
    /**
     * A set of all the classes this student is enrolled in
     */
    private Set<Course> enrolledIn;

    /**
     * Constructor for Student object, just initializes the set
     */
    public Student(){
        enrolledIn = new HashSet<>();
    }

    /**
     * The setter for name
     * @param aName - the name of the student
     */
    public void setName(String aName){
        this.name = aName;
    }

    /**
     * getter for the courses the student is enrolled in
     * @return - returns a set with all the class the student is in
     */
    public Set<Course> getCourses(){
        return enrolledIn;
    }

    /**
     * The method for adding a course to a student's schedule
     * @param aCourse - the course that you want to add
     * @return - boolean indicating success/failure of the operation
     */
    public boolean enrollIn(Course aCourse){
        boolean didEnroll = aCourse.enrollIn(this);
        if(didEnroll) {
            enrolledIn.add(aCourse);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method for removing a course from a student's schedule
     * @param aCourse - the course that you want to remove
     */
    public void drop(Course aCourse){
        if (enrolledIn.contains(aCourse)) {
            enrolledIn.remove(aCourse);
        }
        aCourse.dropStudent(this);
    }
}
