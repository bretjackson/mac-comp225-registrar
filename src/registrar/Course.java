package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Refactored by sduong on 2/23/2016.
 * Courses can have a max enrollment limit on the number of students.
 * The enrollment limit cannot be changed once a student registers for the course.
 */
public class Course {

    private Set<Student> studentsEnrolled; //set of students enrolled
    private List<Student> waitlist; //list of students on the waitlist
    private String courseCode;
    private String courseName;
    private int limit;

    /**
     * Constructor for course, and courses should know the list of students enrolled in them.
     */
    public Course(String number, String title){
        studentsEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
        this.courseCode = number;
        this.courseName = title;
    }


    /**
     * getter method to get the enrollment limit
     * @return limit
     */
    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (studentsEnrolled.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return studentsEnrolled;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    /**
     * checks the enrollment status of the student in the course.
     * @param s
     * @return
     */
    public boolean checkStudentEnrollStatus(Student s){

        //check if student is in the set of studentsEnrolled
        if (studentsEnrolled.contains(s)){
            return true; //return true if student is in the set
        }
        //check if the number of students enrolled is equal or greater than the limit
        if (studentsEnrolled.size() >= limit){
            if (waitlist.contains(s)){
                return false; //if the student is on the waitlist, return false.
            }
            else{
                waitlist.add(s); //otherwise, add student to the waitlist because limit is reached
                return false;
            }
        }
        else{
            studentsEnrolled.add(s); //if the student is not enrolled and there is still spots left, add the student
            return true; //and return true
        }

    }

    public void dropStudent(Student s){
        if (studentsEnrolled.contains(s)) {
            studentsEnrolled.remove(s);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                studentsEnrolled.add(toEnroll);
                toEnroll.courses.add(this);
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }


}
