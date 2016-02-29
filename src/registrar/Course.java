package registrar;

import java.util.*;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> roster;
    private Queue<Student> waitlist;
    private String catalogNumber;
    private String title;
    private int limit;

    /**
     * Constructor
     */
    public Course(){
        roster = new HashSet<>();
        waitlist = new LinkedList<>();
        limit = 16;
    }

    /**
     * Sets the course catalog number
     * @param number
     */
    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    /**
     * Gets catalog number
     * @return
     */
    public String getCatalogNumber(){
        return catalogNumber;
    }

    /**
     * Sets the course's title
     * @param title
     */
    public void setTitle(String title){
        if(title == null){
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = title;
    }

    /**
     * Removes default limit
     */
    public void removeEnrollmentLimit(){
        limit = roster.size();
    }

    /**
     * Gets course's title
     * @return
     */
    public String getTitle(){
        return title;
    }

    /**
     * Gets the course's enrollment limit
     * @return
     */
    public int getEnrollmentLimit(){
        return limit;
    }

    /**
     * Sets course's enrollment limit
     * @param limit
     * @return
     */
    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
//        if (roster.size() == 0){
//            this.limit = limit;
//            return true;
//        }
        // Roster size
        if (roster.size() >= 0){
            this.limit = limit;
            for(Student stu: waitlist){
                enrollNextFromWaitlist();
            }
            return true;
        }
        return false;
    }

    /**
     * Gets a set of students in course
     * @return set of students
     */
    public Set<Student> getStudents(){
        return roster;
    }

    /**
     * Gets the list of students on wait list for course
     * @return list of students
     */
    public Queue<Student> getWaitList(){
        return waitlist;
    }

    /**
     * Enrolls a student in a course
     * @param s student
     * @return
     */
    public boolean enrollIn(Student s){
        if (roster.contains(s)){
            return true;
        }
        else if (roster.size() >= limit){
            if (waitlist.contains(s)){
                return false;
            }
            waitlist.add(s);
            return false;
        }
        roster.add(s);
        return true;
    }

    /**
     * Removes student from enrollment list
     * @param student student
     */
    public void dropStudent(Student student){
        if (roster.contains(student)) {
            roster.remove(student);
            enrollNextFromWaitlist();
        }
        waitlist.remove(student);
    }

    public void enrollNextFromWaitlist(){
        if (!waitlist.isEmpty()){
            waitlist.poll().enrollIn(this);
        }
    }

}
