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
    private String idNumber;
    private String courseName;
    private int enrollmentLimit;

    public Course(){
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollmentLimit = 16;
    }

    public void setCatalogNumber(String number){
        this.idNumber = number;
    }

    public void setTitle(String title){
        this.courseName = title;
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the enrollmentLimit
        if (enrolledStudents.size() == 0){
            this.enrollmentLimit = limit;
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

    public boolean enrollIn(Student student){
        if (enrolledStudents.contains(student)){
            return true;
        }
        else if (enrolledStudents.size() >= enrollmentLimit){
            if (waitlist.contains(student)){
                return false;
            }
            else {
                waitlist.add(student);
                return false;
            }
        }
        else {
            enrolledStudents.add(student);
            student.addToCourses(this);
            return true;
        }
    }

    public void dropStudent(Student student){
        if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
            student.removeFromCourses(this);
            if (waitlist.size() > 0) {
                Student newStudent = waitlist.remove(0);
                enrolledStudents.add(newStudent);
                newStudent.addToCourses(this);
            }

        }
        if (waitlist.contains(student)) {
            waitlist.remove(student);
        }

    }

}


