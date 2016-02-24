package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> enrolledInCourseList;

    public Student(){
        enrolledInCourseList = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return enrolledInCourseList;
    }



    public boolean enrollIn(Course c){
        if(c.enrolled(this)) {
            enrolledInCourseList.add(c);
            System.out.println("Student is succesfully registered in course "+c.getCourseNumber()+" "+c.getCourseTitle());
            return true;
        }
        else{
            System.out.println("Student is in the waitlist for course "+c.getCourseNumber()+" "+c.getCourseTitle());
            return false;
        }
    }

    public void drop(Course c){
        if (enrolledInCourseList.contains(c)) {
            enrolledInCourseList.remove(c);
        }
        c.dropStudent(this);
    }
}
