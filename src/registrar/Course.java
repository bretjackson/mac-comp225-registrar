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
    private int enrollmentLimit;

    private String number;
    private String name;

    public Course(){
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollmentLimit= 16;
    }

    public boolean setEnrollmentLimit(int limit){
        // If students are enrolled you can't change the enrollment limit
        if (enrolledStudents.size() == 0){
            this.enrollmentLimit = limit;
            return true;
        }
        // enrollment limit should be greater than zero
        if (limit <= 0){
            return false;
        }

        return false;
    }

    public boolean enrollIn(Student student){
        // is enrolled
        if (enrolledStudents.contains(student)){
            return true;
        }
        // is not enrolled but added to waitlist
        else if (waitlist.contains(student)){
            return false;
        }
        // not enrolled, not added to waitlist, and class is full, then add to waitlist
        else if (enrolledStudents.size() >= enrollmentLimit){
            waitlist.add(student);
            return false;
        }
        // not enrolled, class not full, add to class
        enrolledStudents.add(student);
        return true;
    }

    public void dropStudent(Student student){
        if (waitlist.contains(student)){
            waitlist.remove(student);
        }
        else if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
            enrollNextOnWaitlist();
        }
    }

    private void enrollNextOnWaitlist() {
        if (waitlist.size() > 0) {
            Student toEnroll = waitlist.remove(0);
            enrolledStudents.add(toEnroll);
            toEnroll.enrollIn(this);
        }
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public void setTitle(String title){
        this.name = title;
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public Set<Student> getStudents(){
        return enrolledStudents;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

}
