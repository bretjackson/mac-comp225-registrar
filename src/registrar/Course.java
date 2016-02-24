package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> enrolledStudents;
    private List<Student> waitlist;
    private String catalogNumber;
    private String courseTitle;
    private int enrollLimit;

    public Course(){
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollLimit = 16;
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public void setCourseTitle(String title){
        this.courseTitle = title;
    }

    public int getEnrollmentLimit(){
        return enrollLimit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the enrollLimit
        if (enrolledStudents.size() == 0){
            this.enrollLimit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getEnrolledStudents(){
        return enrolledStudents;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollStudent(Student student){
        if (enrolledStudents.contains(student)){
            return true;
        }
        if (enrolledStudents.size() >= enrollLimit){
            if (waitlist.contains(student)){
                return false;
            }
            waitlist.add(student);
            return false;
        }
        enrolledStudents.add(student);
        return true;
    }

    public void dropStudent(Student student){
        if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                enrolledStudents.add(toEnroll);
                toEnroll.enrolledCourses.add(this);
            }
        }
        else if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }

}