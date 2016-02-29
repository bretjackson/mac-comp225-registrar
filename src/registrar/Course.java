package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> enrolledIn;
    private List<Student> waitlist;
    private String catalogNumber;
    private String courseName;
    private int limit;

    /**
     * Constructs a new Course object with default of no limit
     */
    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = Integer.MAX_VALUE;
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public void setTitle(String name){
        this.courseName = name;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    /**
     * Sets enrollment limit of the class (if new limit is still above current number of students enrolled)
     * @param limit new limit of class size
     * @return true if limit was reset, false if not reset
     */
    public boolean setEnrollmentLimit(int limit){
        if (limit > enrolledIn.size()) {
            this.limit = limit;
            enrollFromWaitList();
            return true;
        }
        return false;
    }

    /**
     *  Sets enrollment limit of class to infinity (by using MAX_VALUE, all other methods will still work
     *  correctly when checking class sizes to add students, without need to write extra code)
     */
    public void removeEnrollmentLimit() {
        setEnrollmentLimit(Integer.MAX_VALUE);
    }

    public Set<Student> getStudents(){
        return enrolledIn;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    /**
     * Enrolls a student in this class if there is room. If there is not room, student is put on the waitlist.
     * @param student
     * @return true if enrolled successfully, false if not enrolled and put onto waitlist
     */
    public boolean enrollStudent(Student student){
        //case where student is already in course
        if (enrolledIn.contains(student)){
            return true;
        }
        //case where course is full
        if (enrolledIn.size() >= limit){
            if (!waitlist.contains(student)){
                waitlist.add(student); //add student to waitlist if not already on it
            }
            return false;
        }
        //otherwise student can enroll regularly
        enrolledIn.add(student);
        return true;
    }

    /**
     * Removes a student from the course or waitlist. If student is removed from course,
     * the next student on the waitlist is put into the course.
     * @param student Student to be removed from course
     */
    public void dropStudent(Student student){
        if (enrolledIn.contains(student)) {
            enrolledIn.remove(student);
            enrollFromWaitList();
        }
        else if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }

    /**
     * If there are students on the waitlist, fills empty spots in the course with waitlisted students in order
     * of waitlist
     */
    private void enrollFromWaitList() {
        while (limit > enrolledIn.size() && waitlist.size() > 0) {
            Student toEnroll = waitlist.remove(0);
            enrolledIn.add(toEnroll);
            toEnroll.courses.add(this);
        }
    }

}
