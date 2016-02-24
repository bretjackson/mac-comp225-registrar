package registrar;

import java.util.*;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private String catalogNumber;
    private String title;
    private Set<Student> roster;
    private Queue<Student> waitList;
    private int enrollmentLimit;

    public Course(){
        roster = new HashSet<>();
        waitList = new LinkedList<>();
        enrollmentLimit = 16;
    }

    public String getCatalogNumber() {
        return this.catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int enrollmentLimit) {
        //If students are enrolled you can't change the limit
        if (roster.isEmpty()){
            this.enrollmentLimit = enrollmentLimit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return roster;
    }

    public Queue<Student> getWaitList(){
        return waitList;
    }

    public boolean enroll(Student s){
        if (roster.contains(s)){
            return true;
        }
        if (roster.size() >= enrollmentLimit) {
            if (waitList.contains(s)){
                return false;
            }
            waitList.add(s);
            return false;
        }
        roster.add(s);
        return true;
    }

    public void dropStudent(Student s) {
        if (roster.contains(s)) {
            roster.remove(s);
            if (waitList.size() > 0) {
                Student nextStudentWaiting = waitList.poll();
                roster.add(nextStudentWaiting);
                nextStudentWaiting.getCourses().add(this);
            }
        }
        else if (waitList.contains(s)) {
            waitList.remove(s);
        }
    }

}
