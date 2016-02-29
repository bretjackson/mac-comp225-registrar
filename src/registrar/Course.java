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
 */
public class Course {

<<<<<<< HEAD
    private Set<Student> roster = new HashSet<>();
    private List<Student> waitlist = new ArrayList<>();
    private String catalogNumber;
    private String title;
    private int enrollmentLimit = 16;

    public String getCatalogNumber() {
        return catalogNumber;
=======
    private Set<Student> studentsEnrolled;
    /*
     * Set<Student> changed from 'enrolledIn' to 'studentsEnrolled'
     * since it's confusing with the 'enrolledIn' of the Student class.
     */
    private List<Student> waitlist;
    private String number;
    private String title; // changed from 'name' to 'title'.
    private int limit;

    public Course(){
        studentsEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
>>>>>>> 0e3b9fbefe50c96d1c8f121f2237cf47445b5122
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

<<<<<<< HEAD
    public String getTitle() {
        return title;
=======
    public void setTitle(String title){
        this.title = title;
>>>>>>> 0e3b9fbefe50c96d1c8f121f2237cf47445b5122
    }

    public void setTitle(String title) {
        if(title == null) {
            throw new IllegalArgumentException("course title cannot be null");
        }

        this.title = title;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("course cannot have negative enrollment limit: " + limit);
        }

        //If students are enrolled you can't change the limit
<<<<<<< HEAD
        if (!roster.isEmpty()) {
            return false;   // Consider making this IllegalStateException instead of boolean return val
        }

        this.enrollmentLimit = limit;
        return true;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(roster);
=======
        if (studentsEnrolled.size() == 0){
            this.limit = limit;
            return true;
        }
        System.out.println("Cannot set limit as students are already enrolled in this class.");
        // added a message to clarify.
        return false;
    }

    public Set<Student> getStudents(){
        return studentsEnrolled;
>>>>>>> 0e3b9fbefe50c96d1c8f121f2237cf47445b5122
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitlist);
    }
    

<<<<<<< HEAD
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

    public boolean isFull() {
        return roster.size() >= enrollmentLimit;
    }

    private void addToWaitlist(Student s) {
        if (!waitlist.contains(s)) {
            waitlist.add(s);
=======
    /*
    public boolean enrollIn(Student s){
        if (studentsEnrolled.contains(s)){
            return true;
        }
        if (studentsEnrolled.size() >= limit){
            if (waitlist.contains(s)){
                return false;
            }
            waitlist.add(s);
            return false;
        }
        studentsEnrolled.add(s);
        return true;
    }
    */
    // The method, enrollIn, above (commented out) should be replaced by the method, enrollStudent, below.
    public boolean enrollStudent(Student s) {
    	if (this.studentsEnrolled.contains(s)) {
    		System.out.println("Already enrolled");
    		return false;
    	} else if (this.waitlist.contains(s)) {
    		System.out.println("Already waitlisted");
			return false;
    	} else if (this.studentsEnrolled.size() >= limit) {
    		this.waitlist.add(s);
    		System.out.println("This course is full, you are waitlisted");
    		return false;
    	} else {
    		this.studentsEnrolled.add(s);
    		return true;
    	}
    }
    
    public void dropStudent(Student s){
        if (studentsEnrolled.contains(s)) {
            studentsEnrolled.remove(s);
            s.drop(this); // added in case a student gets kicked out of the course.
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.get(0); // first person on waitlist.
                this.waitlist.remove(0); // remove the first person on waitlist.
                studentsEnrolled.add(toEnroll);
                toEnroll.coursesEnrolled.add(this);
            }
>>>>>>> 0e3b9fbefe50c96d1c8f121f2237cf47445b5122
        }
    }

    private void enrollNextFromWaitlist() {
        if (!waitlist.isEmpty()) {
            waitlist.remove(0).enrollIn(this);
        }
    }

    void dropStudent(Student student) {
        waitlist.remove(student);
        if (roster.remove(student)) {
            enrollNextFromWaitlist();
        }
    }

    @Override
    public String toString() {
        return getTitle() + " (" + getCatalogNumber() + ")";
    }
}
