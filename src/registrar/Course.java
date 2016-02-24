package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

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
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
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
    }

    public List<Student> getWaitList(){
        return waitlist;
    }
    

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
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
