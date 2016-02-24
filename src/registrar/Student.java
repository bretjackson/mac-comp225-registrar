package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016. Edited by Rae.
 */

/**
 * This Student class allows each student to know it's name and courses it is enrolled in.
 * A student can drop a course (dropCourse) or attempt to enroll in a course (enrollIn).
 */
public class Student {

    public String name;
    public Set<Course> classSchedule;

    public Student(){
        classSchedule = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getSchedule(){
        return classSchedule;
    }

    /**
     * Allows a student to drop a course. Removes the course from student's class schedule.
     * Removes student from course roster.
     * @param course student wants to drop
     */
    public void dropCourse(Course course){
        classSchedule.remove(course);
        course.dropStudent(this);
    }

    /**
     * Enrolls a student in a course. Returns result of adding student in boolean.
     * @param course student wants to add
     * @return true if student is enrolled in the course and false if student must be added to wait list.
     */
    public boolean enrollIn(Course course){
        if (course.getRoster().contains(this)) {
            return true;
        }
        else if (course.getRoster().size() < course.getEnrollmentLimit()) {
            course.getRoster().add(this);
            classSchedule.add(course);
            return true;
        }
        else if (course.getRoster().size() >= course.getEnrollmentLimit() && !course.getWaitList().contains(this)) {
            course.getWaitList().add(this);
            return false;
        }
        return false;
    }
}
