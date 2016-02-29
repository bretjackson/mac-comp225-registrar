package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * This creates a course. A course has an enrollment limit, class roster, wait list, title, and number.
 * A course can drop a student and pick up students from the wait list.
 */
public class Course {

    private Set<Student> classRoster;
    private List<Student> waitList;
    private String catalogNumber;
    private String title;
    private int limit;

    public Course(){
        classRoster = new HashSet<>();
        waitList = new ArrayList<>();
        limit = 16;
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public Set<Student> getRoster(){
        return classRoster;
    }

    public List<Student> getWaitList(){
        return waitList;
    }

    /**
     * This sets an enrollment limit for a class if no students are in the class
     * If students are enrolled the limit can not be changed.
     * @param limit for class size
     * @return true if limit was changed and false if limit could not be changed
     */
    public boolean setEnrollmentLimit(int limit){
        this.limit = limit;
        while((!waitList.isEmpty()) && classRoster.size() < limit){
            addFromWaitList();
        }
        return true;

    }

    /**
     * Removes the enrollment limit of a course. Sets it to number that will not be reached by any school.
     * Adds all students from wait list.
     */
    public void removeEnrollmentLimit(){
        setEnrollmentLimit(100000);
        while(!waitList.isEmpty()){
           addFromWaitList();
        }
    }

    /**
     * Students are dropped from a class. They are removed from a class roster and if there is a wait list
     * students from the wait list are automatically added to the class roster. Students can also drop from wait list.
     * Student changes schedule if course is still in student schedule.
     * @param student The student that is dropping from the course.
     */
    public void dropStudent(Student student){
        if (classRoster.contains(student)) {
            classRoster.remove(student);
            student.dropCourse(this);
            addFromWaitList();
        }
        else if (waitList.contains(student)){
            waitList.remove(student);
        }
    }


    /**
     * Enrolls a student in course
     * @param student to be enrolled
     * @return true if successfully enrolled. False if waitlisted
     */
    public boolean enrollStudent(Student student) {
        if (classRoster.contains(student)) {
            return true;
        }
        if (classRoster.size() > this.getEnrollmentLimit()) {
            addToWaitlist(student);
            return false;
        }
        classRoster.add(student);
        return true;
    }

    /**
     * Adds student to course from wait list.
     */
    private void addFromWaitList(){
        if (waitList.size() > 0) {
            Student toEnroll = waitList.remove(0);
            classRoster.add(toEnroll);
            toEnroll.enrollIn(this);
        }
    }

    /**
     * Adds student to wait list.
     * @param student
     */
    private void addToWaitlist(Student student) {
        if (!waitList.contains(student)) {
            waitList.add(student);
        }
    }

}
