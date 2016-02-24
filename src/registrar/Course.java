package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> enrolledIn;
    private List<Student> waitlist;
    private String number;
    private String name;
    private int limit;

    /*
    Constructor for a course
     */
    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    /*
    Sets the catalog Number of a course
     */
    public void setCatalogNumber(String number){
        this.number = number;
    }

    /**
     * sets the title of a course
     * @param title
     */
    public void setTitle(String title){
        this.name = title;
    }

    /**
     * Gets the enrollment limit for a course
     * @return the enrollment limit for a course
     */
    public int getEnrollmentLimit(){
        return limit;
    }

    /**
     * Sets the enrollment limit of a course
     * @param limit is the number of openings
     * @return false if the enrollment limit has not been set
     */
    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (enrolledIn.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
    }


    /**
     * gets the students in enrolledIn
     * @return a set of students that are enrolled in the course
     */
    public Set<Student> getStudents(){
        return enrolledIn;
    }

    /**
     *
     * @return the waitlist of students
     */
    public List<Student> getWaitList(){
        return waitlist;
    }

    /**
     * Enrolls a student in a course
     * @param s the student to be enrolled in a course
     * @return true if the student is successfully enrolled, false if they are added to the waitlist
     */
    public boolean enrollIn(Student s){
        //Checks to see if student is already enrolled
        if (enrolledIn.contains(s)){
            return true;
        }
        if (enrolledIn.size() >= limit){
            //Checks to see if student is already on the waitlist
            if (waitlist.contains(s)){
                return false;
            }
            waitlist.add(s);
            return false;
        }
        enrolledIn.add(s);
        return true;
    }

    /**
     * Drops a student from a class or from a class's wait list
     * @param s is a student to be removed
     */
    public void dropStudent(Student s){
        //If in a class, remove them
        if (enrolledIn.contains(s)) {
            enrolledIn.remove(s);
            editWaitList();
        }
        //If on the waitlist, remove them
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }


    /**
     * Removes a student from the wait list and adds them to enrolled
     */
    public void editWaitList(){
        if (waitlist.size() > 0) {
            Student toEnroll = waitlist.remove(0);
            enrolledIn.add(toEnroll);
            toEnroll.enrolledIn.add(this);
        }
    }

}