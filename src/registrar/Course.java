package registrar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Edited by Tyler Skluzacek on 3/12/2016
 *
 * This class allows programs to create a course, complete with class rosters, waitlists,
 * catalog numbers, course title, and the enrollment limit.
 *
 * NEW ADDS: On 3/12/2016, I added a 'removeEnrollmentLimit()' class that takes the given
 * enrollment limit and turns it into infinity (or the Integer.MAX_VALUE in java).
 *
 */

public class Course {

    private Set<Student> roster = new HashSet<>();
    private List<Student> waitlist = new ArrayList<>();
    private String catalogNumber;
    private String title;
    private int enrollmentLimit = (Integer.MAX_VALUE - 1);


    public String getCatalogNumber() {
        return catalogNumber;
    }

    /** This new removeEnrollmentLimit() method allows the user to get the enrollment limit,
     * and if it is not infinity (or technically, the maximum integer value), then it sets it
     * to infinity (max integer value).
     */
    public void removeEnrollmentLimit() {
        if (getEnrollmentLimit() != Integer.MAX_VALUE) {
            setEnrollmentLimit(Integer.MAX_VALUE/2);
        }
    }

    /**
     *
     * @param catalogNumber = the catalog number of the class, as a string. E.g. "COMP 225"
     */
    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title = a string representing the course title. E.g. "Software Development"
     */
    public void setTitle(String title) {
        if(title == null) {
            throw new IllegalArgumentException("course title cannot be null");
        }

        this.title = title;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    /**
     *
     * @param limit = the largest number of people who can be enrolled in a class
     *             before the next person is banished to the waitlist
     *
     */
    public boolean setEnrollmentLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("course cannot have negative enrollment limit: " + limit);
        }

        //If students are enrolled you can't change the limit
        if (!roster.isEmpty()) {
            return false;   // Consider making this IllegalStateException instead of boolean return val
        }

        this.enrollmentLimit = limit;
        return true;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(roster);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitlist);
    }

    /** Allows the user to enroll a student**/
    boolean enroll(Student student) {
        if (roster.contains(student)) {
            return true;
        }
        if (isFull()) {
            addToWaitlist(student);
            return false;
        }
        roster.add(student);
        return true;
    }

    /**Returns if the class is full or not.**/
    public boolean isFull() {
        return roster.size() >= enrollmentLimit;
    }

    /**Put a student onto the waitList**/
    private void addToWaitlist(Student s) {
        if (!waitlist.contains(s)) {
            waitlist.add(s);
        }
    }

    /**When a student is dropped, one student is taken off of waitlist**/
    private void enrollNextFromWaitlist() {
        if (!waitlist.isEmpty()) {
            waitlist.remove(0).enrollIn(this);
        }
    }

    /** A student is cut from the class**/
    void dropStudent(Student student) {
        waitlist.remove(student);
        if (roster.remove(student)) {
            enrollNextFromWaitlist();
        }
    }

    /**A simple to-string method that allows us to put the course title and catalog number
     * together.
     * @return = that string.
     */
    @Override
    public String toString() {
        return getTitle() + " (" + getCatalogNumber() + ")";
    }
}
