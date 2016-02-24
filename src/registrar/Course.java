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

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (getStudents().size() != 0){
            this.limit = limit;
            return true;
        }
        else{
            return false;
        }
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public Set<Student> getStudents(){
        return enrolledIn;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollIn(Student s){
        if (getStudents().contains(s)){ /*if student is already on enrolled List, return True */
            return true;
        }
        if (addToWaitList(s)) {
            return false;
        }
        getStudents().add(s);
        return true;
    }

    public boolean addToWaitList(Student s) {
        /*
        Adds student to waitlist if enrolled list is full and not already on the waitlist.
         */
        if (getStudents().size() >= limit){ /*if enrolled list is greater than or equal to the limit */
            if (getWaitList().contains(s)){ /*if student is already in the wait list */
                return true;
            }
            getWaitList().add(s); /*add student to wait list*/
            return true;
        }
        return false;
    }

    public void dropStudent(Student s){
        /*
        if enrolled, remove student from enrolled list and adds first student off the waiting list.
        if on the waiting list, just remove student.
         */
        if (getStudents().contains(s)) {
            enrolledIn.remove(s);
            addOffWaitList();
        }
        else if (getWaitList().contains(s)){
            waitlist.remove(s);
        }
    }

    public void addOffWaitList() {
        /*
        If waiting list is not empty, add student off waiting list to enrolled list.
         */
        if (getWaitList().size() > 0) { /*if waiting list not empty_ */
            Student toEnroll = waitlist.remove(0);
            enrolledIn.add(toEnroll);
            toEnroll.enrolledIn.add(this);
        }
    }
}
