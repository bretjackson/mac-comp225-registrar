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
    private String number;
    private String title;
    private int limit;

    public Course(){
        classRoster = new HashSet<>();
        waitList = new ArrayList<>();
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
        if (classRoster.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
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
            student.classSchedule.remove(this);
            if (waitList.size() > 0) {
                Student toEnroll = waitList.remove(0);
                classRoster.add(toEnroll);
                toEnroll.classSchedule.add(this);
            }
        }
        else if (waitList.contains(student)){
            waitList.remove(student);
        }
    }

}
