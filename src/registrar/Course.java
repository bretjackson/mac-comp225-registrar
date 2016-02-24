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
    private List<Student> waitlist;
    private String catalogNumber;
    private String title;
    private int enrollmentLimit;

    public Course(){
        studentsEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollmentLimit = 16;
    }

    public void setCatalogNumber(String number){ this.catalogNumber = number; }

    public void setTitle(String title){
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (studentsEnrolled.isEmpty()) {
            this.enrollmentLimit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getEnrolledStudents(){
        return studentsEnrolled;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrolled(Student s){
        if (studentsEnrolled.contains(s)){
            return true;
        }
        if (studentsEnrolled.size() >= enrollmentLimit){
            if (waitlist.contains(s)){
                return false;
            }
            waitlist.add(s);
            return false;
        }
        studentsEnrolled.add(s);
        return true;
    }

    public void dropStudent(Student s){
        if (studentsEnrolled.contains(s)) {
            studentsEnrolled.remove(s);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                studentsEnrolled.add(toEnroll);
                toEnroll.coursesEnrolled.add(this);
            }
        }
        // removes s from waitlist if he/she is on waitlist
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
        else if (!(studentsEnrolled.contains(s)) && !(waitlist.contains(s))) {
            System.out.println("Student " + s + " not enrolled in course or on waitlist");
        }
    }

}
