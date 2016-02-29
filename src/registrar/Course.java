package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course
{

    private Set<Student> roster;
    private List<Student> waitlist;
    private String number;
    private String name;
    private int limit;

    //limit when -1 means that the course has no limit off this recommendation: http://stackoverflow.com/questions/21999750/how-to-set-to-int-value-null-java-android
    public Course()
    {
        roster = new HashSet<>();
        waitlist = new ArrayList<>();
        int limit = -1;
    }

    public void setCatalogNumber(String coursenumber)
    {
        this.number = coursenumber;
    }

    public void setTitle(String courseTitle)
    {
        this.name = courseTitle;
    }

    public int getEnrollmentLimit()
    {
        return limit;
    }

    public boolean setEnrollmentLimit(int newLimit)
    {
        //If students are enrolled you can't change the limit
        if (roster.size() > newLimit)
        {
            return false;
        }
        // return false if students have already enrolled indicating the limit can't be changed
        else{
            limit = newLimit;
            return true;
        }
    }

    //these look fine...?
    public Set<Student> getStudents()
    {
        return roster;
    }

    public List<Student> getWaitList()
    {
        return waitlist;
    }

    public boolean enrollIn(Student newStudent)
    {
        if (roster.contains(newStudent))
        {
            //catch a student already being enrolled
            return true;
        }
        //if student can be added to class, add them
        if(this.limit == -1){
            roster.add(newStudent);
            return true;
        }
        else if (roster.size() < this.limit)
        {
            roster.add(newStudent);
            return true;
        }
        //else fit them to the waitlist
        else if (roster.size() >= limit && !waitlist.contains(newStudent))
        {
            waitlist.add(newStudent);
        }
        return false;
    }

    public void dropStudent(Student studentRemove)
    {
        if (roster.contains(studentRemove))
        {
            roster.remove(studentRemove);
            if (waitlist.size() > 0)
            {
                //I feel like Queue would make more sense for waitlist than a list but when I convert queue into a list it tries to override all the queue functions.
                Student studentToEnroll = waitlist.remove(0);
                studentToEnroll.enrollIn(this);
            }
        }
        if (waitlist.contains(studentRemove)){
            waitlist.remove(studentRemove);
        }
    }

    public void dropStudentFromWaitlist(Student s)
    {
        //quick function for removing a student on the waitlist
        if (waitlist.contains(s))
        {
            waitlist.remove(s);
        }
    }

    public void removeEnrollmentLimit(){
        this.limit = -1;
    }

}