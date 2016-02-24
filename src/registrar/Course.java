package registrar;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {
    private Set<Student> roster;
    private List<Student> waitlist;
    private String catalogNumber;
    private String title;
    private int enrollmentLimit;

    public Course(){
        roster = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollmentLimit = 16;
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (roster.isEmpty()){
            this.enrollmentLimit = limit;
            return true;
        }
        else{
            return false;
        }
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public Set<Student> getStudents(){
        return roster;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollIn(Student student){
        if (getStudents().contains(student)){ /*if student is already on enrolled List, return True */
            return true;
        }
        if (addToWaitList(student)) {
            return false;
        }
        getStudents().add(student);
        return true;
    }

    public boolean addToWaitList(Student student) {
        /*
        Adds student to waitlist if enrolled list is full and not already on the waitlist.
         */
        if (roster.size() >= enrollmentLimit){ /*if enrolled list is greater than or equal to the limit */
            if (getWaitList().contains(student)){ /*if student is already in the wait list */
                return true;
            }
            getWaitList().add(student); /*add student to wait list*/
            return true;
        }
        return false;
    }

    public void dropStudent(Student student){
        /*
        if enrolled, remove student from enrolled list and adds first student off the waiting list.
        if on the waiting list, just remove student.
         */
        if (getStudents().contains(student)) {
            roster.remove(student);
            addOffWaitList();
        }
        else if (getWaitList().contains(student)){
            waitlist.remove(student);
        }
    }

    public void addOffWaitList() {
        /*
        If waiting list is not empty, add student off waiting list to enrolled list.
         */
        if (!waitlist.isEmpty()) { /*if waiting list not empty_ */
            Student studentToAdd = waitlist.remove(0);
            roster.add(studentToAdd);
            studentToAdd.enrolledIn.add(this);
        }
    }
}