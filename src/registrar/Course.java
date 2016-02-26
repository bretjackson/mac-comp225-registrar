package registrar;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

/**
 * Created by bjackson on 2/21/2016.
 * Keeps track of students enrolled in course and manages the course wait list.
 */
public class Course {
    private String catalogNumber;
    private String title;
    private int enrollmentLimit;

    private Set<Student> enrolled;
    private List<Student> waitList;

    public Course(){
        this(null, null, 16);
    }

    public Course(String catalogNumber, String title, int enrollmentLimit) {
        this.catalogNumber = catalogNumber;
        this.title = title;
        this.enrollmentLimit = enrollmentLimit;
        enrolled = new HashSet<Student>();
        waitList = new LinkedList<Student>();
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getEnrollmentLimit(){
        return this.enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (enrolled.isEmpty() && limit >= 0){
            this.enrollmentLimit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return Collections.unmodifiableSet(enrolled);
    }

    public List<Student> getWaitList(){
        return Collections.unmodifiableList((List) waitList);
    }

    private void addToWaitList(Student student) {
        if (!waitList.contains(student)){
            waitList.add(student);
        }
    }

    /*
     * Enrolls student in course or adds student to wait list if enrollment limit reached.
     * @return true if student already enrolled or successfully enrolled, false if student added to wait list
     */
    public boolean enroll(Student student){
        if (enrolled.size() < enrollmentLimit || enrolled.contains(student)) {
            enrolled.add(student);
            return true;
        } else {
            addToWaitList(student);
            return false;
        }
    }

    private void enrollNextWaitListed() {
        if (waitList.size() > 0) {
            Student nextStudent = waitList.remove(0);
            enrolled.add(nextStudent);
            nextStudent.enrolledCourses.add(this);
        }
    }

    /*
     * Removes student from set of enrolled students or wait list if in either
     */
    public void dropStudent(Student s){
        if (enrolled.contains(s)) {
            enrolled.remove(s);
            enrollNextWaitListed();
        }
        else if (waitList.contains(s)){
            waitList.remove(s);
        }
    }

}
