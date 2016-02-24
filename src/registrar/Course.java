package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> students; // was enrolledin
    private List<Student> waitlist;
    private String number;
    private String title;
    private int limit;

    public Course(){
        students = new HashSet<>();
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

    /**
     * So that one can change the enrollment limit
     * @param limit
     * @return
     */
    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (students.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return students;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    /**
     * Returns whether or not a specified student is enrolled in a course, and if not, enrolls the student
     * @param s
     * @return
     */
    public boolean isEnrolled(Student s){
        if (students.contains(s)){
            return true;
        }
        if (students.size() >= limit){
            if (waitlist.contains(s)){
                return false;
            }
            waitlist.add(s);
            return false;
        }
        students.add(s);
        return true;
    }

    /**
     * Removes a specific student from this course and adds the next student on the waitlist
     * @param s
     */
    public void dropStudent(Student s){
        if (students.contains(s)) {
            students.remove(s);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                students.add(toEnroll);
                toEnroll.getCourses().add(this);
                //isEnrolled(toEnroll);
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
