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
    private ArrayList<Student> waitlist;
    private String catalogNumber;
    private String courseTitle;
    private int enrollLimit;

    public Course(){
        this.studentsEnrolled = new HashSet<>();
        this.waitlist = new ArrayList<>();
    }

    public boolean enrollIn(Student s){
        if (studentsEnrolled.contains(s)){
            return true;
        }
        if (studentsEnrolled.size() >= enrollLimit){
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
                toEnroll.enrolledIn.add(this);
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

    public void removeEnrollmentLimit() {
        this.enrollLimit = Integer.MAX_VALUE;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCatalogNumber(String catalogNumber){
        this.catalogNumber = catalogNumber;
    }

    public void setTitle(String title){
        this.courseTitle = title;
    }

    public int getEnrollmentLimit(){
        return enrollLimit;
    }

    public boolean setEnrollmentLimit(int enrollLimit){
        this.enrollLimit = enrollLimit;
        return true;
    }

    public Set<Student> getStudents(){
        return studentsEnrolled;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }
}
