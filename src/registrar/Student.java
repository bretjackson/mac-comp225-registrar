package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> enrolledIn;

    public Student(){
        enrolledIn = new HashSet<>();
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
   public Set<Course> getCourses(){
        return enrolledIn;
    }

    public boolean enrollIn(Course course) {

      if(course.getEnrollmentLimit() >= course.getStudents().size()){
            //if student is enrolled or can successfully enroll, student can add course to his list of courses
            if (course.enrollIn(this)) {
                enrolledIn.add(course);
                return true;
            } else {
                return false;
          }
      }
      else{
          return false;
      }

    }


    public void drop(Course course){
        if (enrolledIn.contains(course)) {
            enrolledIn.remove(course);
        }
        course.dropStudent(this);
    }
}
