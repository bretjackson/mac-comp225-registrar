package registrar;

import java.util.*;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> students;
    private List<Student> waitlist;
    private String catalogNumber;
    private String courseTitle;
    private int limit;

    public Course(){
        students = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    public void setCatalogNumber(String number){
        catalogNumber = number;
    }

    public void setTitle(String title){
        this.courseTitle = title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

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
     * Check whether the student is already enrolled. If the student is enrolled, return true.
     * If the student size is equal or larger than limit, and if the student is on the waitlist, return false.
     *
     * @param s
     * @return
     */
    public boolean enrollIn(Student s){
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

    public void dropStudent(Student s){
        if (students.contains(s)) {
            students.remove(s);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                students.add(toEnroll);
                toEnroll.courses.add(this);
            }
        }

        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }

    }

}
