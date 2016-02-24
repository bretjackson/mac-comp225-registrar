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

    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
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

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (enrolledIn.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return enrolledIn;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollIn(Student s){
        if (enrolledIn.contains(s)){
            return true;
        }
        if (enrolledIn.size() >= limit){
            putOnWaitlist(s);
            return false;
        }
        enrolledIn.add(s);
        return true;
    }

    public boolean putOnWaitlist(Student s) {
        if (waitlist.contains(s)){
            return true;
        }
        waitlist.add(s);
        return true;
    }

    public void dropStudent(Student s){
        if (enrolledIn.contains(s)) {
            enrolledIn.remove(s);
            enrollWaitList(s);
        }
        removeWaitList(s);
    }

    public void enrollWaitList(Student s){
        if (waitlist.size()>0){
            Student toEnroll = waitlist.remove(0);
            enrolledIn.add(toEnroll);
            toEnroll.enrolledIn.add(this);
        }
    }

    public void removeWaitList(Student s){
        if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
