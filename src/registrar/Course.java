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
    private String name;
    private int limit;

    public Course(){
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = Integer.MAX_VALUE;
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
        if (this.limit != limit) {
            this.limit = limit;
            return true;
        }
        return true;
    }

    public void removeEnrollmentLimit(){
        this.limit = Integer.MAX_VALUE;
    }

    public Set<Student> getStudents(){
        return enrolledStudents;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean addStudent(Student student){
        if (enrolledStudents.contains(student)){
            return true;
        }
        if (enrolledStudents.size() >= limit){
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
