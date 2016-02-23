package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    public String catalogNumber;
    public String title;

    private Set<Student> participants;
    private List<Student> waitList;
    private int enrollmentLimit;

    public Course(){
        participants = new HashSet<>();
        waitList = new ArrayList<>();
        enrollmentLimit = 16;
    }

    public void setCatalogNumber(String catalogNumber){
        this.catalogNumber = catalogNumber;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int enrollmentLimit){
        //If students are enrolled you can't change the limit
        if (participants.size() == 0){
            this.enrollmentLimit = enrollmentLimit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return participants;
    }

    public List<Student> getWaitList(){
        return waitList;
    }

    public boolean enrollIn(Student s){
        if (participants.contains(s)){
            return true;
        }
        if (participants.size() >= enrollmentLimit){
            if (waitList.contains(s)){
                return false;
            }
            waitList.add(s);
            return false;
        }
        participants.add(s);
        return true;
    }

    public void dropStudent(Student s){
        if (participants.contains(s)) {
            participants.remove(s);
            if (waitList.size() > 0) {
                Student nextStudentWaiting = waitList.remove(0);
                participants.add(nextStudentWaiting);
                nextStudentWaiting.enrolledCourses.add(this);
            }
        }
        else if (waitList.contains(s)){
            waitList.remove(s);
        }
    }

}
