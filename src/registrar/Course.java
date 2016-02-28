package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Edited by MarcioPorto for COMP 225 Refactoring activity
 */
public class Course {

    private Set<Student> studentsEnrolled;
    private List<Student> waitList;
    private String catalogNumber;
    private String title;
    private int enrollmentLimit;

    public Course(){
        studentsEnrolled = new HashSet<>();
        waitList = new ArrayList<>();
        enrollmentLimit = -1;           // unlimited enrollment
    }

    public Set<Student> getStudents(){
        return studentsEnrolled;
    }

    public List<Student> getWaitList(){
        return waitList;
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    /*
    * This function sets the enrollment enrollmentLimit for a course.
    * @param  limit: the maximum number of students
    * */
    public void setEnrollmentLimit(int limit){
        this.enrollmentLimit = limit;
    }

    /*
    * This function adds a student to the studentsEnrolled list of a course.
    * @param  s the student joining the course
    * @return true if successful, false otherwise
    * */
    public boolean addStudent(Student s){
        // If the student is already in the list, just returns true
        if (studentsEnrolled.contains(s)){
            return true;
        }
        // If the class is full, add student to waitList
        if (enrollmentLimit != -1 && studentsEnrolled.size() >= enrollmentLimit){
            // If the student is already on the waitList, return false
            if (waitList.contains(s)){
                return false;
            }
            waitList.add(s);
            return false;
        }
        // Add student to the studentsEnrolled list
        studentsEnrolled.add(s);
        return true;
    }

    /*
    * If the student is enrolled in a course, this function drops the student from the course's studentsEnrolled list.
    * If the student is on the waitList, this function removes the student from it.
    * @param  s the student to be dropped
    * @return void
    * */
    public void dropStudent(Student s){
        if (studentsEnrolled.contains(s)) {
            studentsEnrolled.remove(s);
            // If there is someone on the waitList, add that person to the studentsEnrolled list
            if (waitList.size() > 0) {
                addFromWaitList();
            }
        }
        else if (waitList.contains(s)){
            waitList.remove(s);
        }
    }

    /*
    * Adds the first student from the waitList to the studentsEnrolled list (removing it from the waitList afterwards).
    * @return void
    * */
    public void addFromWaitList(){
        Student studentFromWaitList = waitList.remove(0);
        studentsEnrolled.add(studentFromWaitList);
        studentFromWaitList.joinCourse(this);
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void removeEnrollmentLimit() {
        this.enrollmentLimit = -1;
    }

}
