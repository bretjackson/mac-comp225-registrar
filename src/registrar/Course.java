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
    private String number;
    private String title;
    private int limit;

    public Course() {
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public String getCatalogNumber(String number){
        return this.number;
    }

    public void setTitle(String title){
        this.title = title;
    }
    
    public String getTitle(String title){
        return title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (enrolledStudents.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return enrolledStudents;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollStudent(Student s){
        if (isWaitListable(s)){
            addStudentToWaitList(s);
            return false;
        } else if (isEnrollable(s)) {
            enrolledStudents.add(s);
        }
        return true;
    }

    public void dropStudent(Student s){
        if (enrolledStudents.contains(s)) {
            enrolledStudents.remove(s);
            enrollNextOnWaitList();
        }
        waitlist.remove(s);
    }

    private void enrollNextOnWaitList () {
        if (waitlist.size() > 0) {
            Student toEnroll = waitlist.remove(0);
            enrolledStudents.add(toEnroll);
            toEnroll.getCourses().add(this);
        }
    }

    private void addStudentToWaitList (Student s) {
        if (!waitlist.contains(s)) {
            waitlist.add(s);
        }
    }

    private boolean isWaitListable (Student s) {
        return (!enrolledStudents.contains(s) &&
                enrolledStudents.size() >= limit);
    }

    private boolean isEnrollable (Student s) {
        return (!enrolledStudents.contains(s) ||
                enrolledStudents.size() < limit);
    }


}
