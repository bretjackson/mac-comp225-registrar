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

    /**
     * Constructor
     */
    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    /**
     * Sets the course catalog number
     * @param number
     */
    public void setCatalogNumber(String number){
        this.number = number;
    }

    /**
     * Sets the course title
     * @param title
     */
    public void setTitle(String title){
        this.name = title;
    }

    /**
     * Gets the course's enrollment limit
     * @return
     */
    public int getEnrollmentLimit(){
        return limit;
    }

    /**
     * Sets course's enrollment limit
     * @param limit
     * @return
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
     * Gets a set of students in course
     * @return set of students
     */
    public Set<Student> getStudents(){
        return enrolledIn;
    }

    /**
     * Gets the list of students on wait list for course
     * @return list of students
     */
    public List<Student> getWaitList(){
        return waitlist;
    }

    /**
     * Checks if student is enrolled
     * @param s student
     * @return
     */
    public boolean enrollIn(Student s){
        if (enrolledIn.contains(s)){
            return true;
        }
        if (enrolledIn.size() >= limit){
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
     * Removes student from enrollment list
     * @param s student
     */
    public void dropStudent(Student s){
        if (enrolledIn.contains(s)) {
            enrolledIn.remove(s);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                enrolledIn.add(toEnroll);
                toEnroll.enrolledIn.add(this);
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
