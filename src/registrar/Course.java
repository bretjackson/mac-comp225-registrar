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

    public Course(){
        enrolledStudents = new HashSet<>();
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

    public String getCourseTitle(){
        return title;
    }
    
    public String getCourseNumber(){
        return number;
    }

    public boolean enrolled(Student s) {
        if (enrolledStudents.contains(s)) {
            return true;
        }
        else if (enrolledStudents.size() >= limit) {
            if (!waitlist.contains(s)) {
                waitlist.add(s);
            }
            return false;
        }
        else {
            enrolledStudents.add(s);
                return true;
            }
    }

    public void dropStudent(Student s){
        if (enrolledStudents.contains(s)) {
            enrolledStudents.remove(s);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                enrolledStudents.add(toEnroll);
                toEnroll.enrolledInCourseList.add(this);
            }
        }
        if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
