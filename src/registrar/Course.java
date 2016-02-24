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
    private int limit;

    private String number;
    private String name;

    public Course(){
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    public boolean setEnrollmentLimit(int limit){
        // If students are enrolled you can't change the limit
        if (enrolledStudents.size() == 0){
            this.limit = limit;
            return true;
        }
        // limit should be greater than zero
        if (limit <= 0){
            return false;
        }

        return false;
    }

    public boolean enrollIn(Student s){
        // is enrolled
        if (enrolledStudents.contains(s)){
            return true;
        }
        // is not enrolled but added to waitlist
        else if (waitlist.contains(s)){
            return false;
        }
        // not enrolled, not added to waitlist, and class is full, then add to waitlist
        else if (enrolledStudents.size() >= limit){
            waitlist.add(s);
            return false;
        }
        // not enrolled, class not full, add to class
        enrolledStudents.add(s);
        return true;
    }

    public void dropStudent(Student s){
        if (waitlist.contains(s)){
            waitlist.remove(s);
        }
        else if (enrolledStudents.contains(s)) {
            enrolledStudents.remove(s);
            enrollFirstInWaitlisht();
        }
    }

    private void enrollFirstInWaitlisht() {
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
        return limit;
    }

    public Set<Student> getStudents(){
        return enrolledStudents;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

}
