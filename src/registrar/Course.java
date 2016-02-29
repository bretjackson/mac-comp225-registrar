package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {
    /**
     * A set of the students enrolled in this course
     */
    private Set<Student> enrolledIn;
    /**
     * A list of students waiting to enter this course
     */
    private List<Student> waitlist;
    /**
     * The first four letters of the course's deparment, and it's unique number
     */
    private String courseID;
    /**
     * The title of the course
     */
    private String courseTitle;
    /**
     * The maximum number of students allowed to enroll in this course
     */
    private int limit;

    /**
     * A constructor for Course objects, just initializes the data structures. IMPORTANT:
     * You need to first set the course id, title, and limit before using the object
     */
    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = -1;
        //took out so that it just initializes to 0, why was it 16 before, that seems arbitrary you have to se
        //the limit now like you have to with courseID, and name
    }

    /**
     * Setter for courseID
     * @param aIdNumber - the first four letters of the coures's department, and it's number
     */
    public void setCourseID(String aIdNumber){
        this.courseID = aIdNumber;
    }

    /**
     * Setter for the course title
     * @param name - the name of the course
     */
    public void setTitle(String name){
        this.courseTitle = name;
    }

    /**
     * Getter for the limit
     * @return - the maximum number of students than can enroll in this course
     */
    public int getEnrollmentLimit(){
        return limit;
    }

    /**
     * Setter for the capacity of a class, you can only change the capacity if no students are enrolled
     * @param limit - the new capacity that you would like to use
     * @return returns a boolean to indicate success/failure of the operation
     */
    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (limit <= enrolledIn.size() && !(limit < 0)){ //Now it will let you change the limit if students are already enrolled, but not if the new limit is too low
            return false;
        }
        this.limit = limit;
        return true;
    }

    /**
     * Removes the enrollment limit from a course
     * @return I know it wasn't supposed to return anything, but it made testing much easier to return a success/fail boolean
     */
    public boolean removeEnrollmentLimit(){
        return setEnrollmentLimit(-1);
    }

    /**
     * getter for the the set of students enrolled in this course
     * @return - a set of all students enrolled in this course
     */
    public Set<Student> getStudents(){
        return enrolledIn;
    }

    /**
     * getter for the wait list
     * @return - a list, in order, of all the students waiting to enroll in this class
     */
    public List<Student> getWaitList(){
        return waitlist;
    }

    /**
     * Method for adding a student to this course
     * @param aStudent the student you want to enter this course
     * @return success/failure in enrolling, in case of failure the student is added to the waitlist
     */
    public boolean enrollIn(Student aStudent){
        if (enrolledIn.contains(aStudent)){
            return true;
        } else {
            if (enrolledIn.size() >= limit && !(limit < 0)){
                if (waitlist.contains(aStudent)){
                    return false;
                }
                waitlist.add(aStudent);
                return false;
            }
            enrolledIn.add(aStudent);
            return true;
        }
    }

    /**
     * Method to removes a student from this course. Automatically adds the next student on the wait list
     * to the class.
     * @param aStudent - the student that you want to drop from this course
     */
    public void dropStudent(Student aStudent){
        if (enrolledIn.contains(aStudent)) {
            enrolledIn.remove(aStudent);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                enrolledIn.add(toEnroll);
                toEnroll.enrollIn(this); //Changed to use the method, rather than private variables
            }
        }
        else if (waitlist.contains(aStudent)){
            waitlist.remove(aStudent);
        }
    }

}
