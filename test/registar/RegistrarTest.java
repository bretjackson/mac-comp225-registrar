package registar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import registrar.Course;
import registrar.Student;

import static org.junit.Assert.*;

public class RegistrarTest {

    // ------ Setup ------

    private TestObjectFactory factory = new TestObjectFactory();
    private Course comp225, math6, basketWeaving101;
    private Student sally, fred, zongo;

    @Before
    public void createStudents() {
        sally = factory.makeStudent("Sally");
        fred  = factory.makeStudent("Fred");
        zongo = factory.makeStudent("Zongo Jr.");
    }

    @Before
    public void createCourses() {
        comp225 = factory.makeCourse("COMP 225", "Software Fun Fun");
        comp225.setEnrollmentLimit(16);

        math6 = factory.makeCourse("Math 6", "All About the Number Six");

        basketWeaving101 = factory.makeCourse("Underwater Basket Weaving 101", "Senior spring semester!");
    }

    // ------ Enrolling ------

    @Test
    public void studentStartsInNoCourses() {
        assertEquals(Collections.emptySet(), sally.getCourses());
    }

    @Test
    public void studentCanEnroll() {
        sally.enrollInCourse(comp225);
        assertEquals(set(comp225), sally.getCourses());
        assertEquals(set(sally), comp225.getEnrolledStudents());
    }

    @Test
    public void doubleEnrollingHasNoEffect() {
        sally.enrollInCourse(comp225);
        sally.enrollInCourse(comp225);
        assertEquals(set(comp225), sally.getCourses());
        assertEquals(set(sally), comp225.getEnrolledStudents());
    }


    // ------ Enrollment limits ------

    @Test
    public void coursesHaveEnrollmentLimits() {
        comp225.setEnrollmentLimit(16);
        assertEquals(16, comp225.getEnrollmentLimit());
    }

    @Test
    public void enrollingUpToLimitAllowed() {
        factory.enrollMultipleStudents(comp225, 15);
        assertTrue(sally.enrollInCourse(comp225));
        assertEquals(list(), comp225.getWaitList());
        assertTrue(comp225.getEnrolledStudents().contains(sally));
    }

    @Test
    public void enrollingPastLimitPushesToWaitList() {
        factory.enrollMultipleStudents(comp225, 16);
        assertFalse(sally.enrollInCourse(comp225));
        assertEquals(list(sally), comp225.getWaitList());
        assertFalse(comp225.getEnrolledStudents().contains(sally));
    }

    @Test
    public void waitListPreservesEnrollmentOrder() {
        factory.enrollMultipleStudents(comp225, 16);
        sally.enrollInCourse(comp225);
        fred.enrollInCourse(comp225);
        zongo.enrollInCourse(comp225);
        assertEquals(list(sally, fred, zongo), comp225.getWaitList());
    }

    @Test
    public void doubleEnrollingInFullCourseHasNoEffect() {
        sally.enrollInCourse(comp225);
        factory.enrollMultipleStudents(comp225, 20);
        assertTrue(sally.enrollInCourse(comp225)); // full now, but Sally was already enrolled
        assertTrue(comp225.getEnrolledStudents().contains(sally));
        assertFalse(comp225.getWaitList().contains(sally));
    }

    @Test
    public void doubleEnrollingAfterWaitListedHasNoEffect() {
        factory.enrollMultipleStudents(comp225, 16);
        sally.enrollInCourse(comp225);
        fred.enrollInCourse(comp225);
        zongo.enrollInCourse(comp225);
        fred.enrollInCourse(comp225);
        assertFalse(sally.enrollInCourse(comp225));

        assertEquals(list(sally, fred, zongo), comp225.getWaitList());
    }

    @Test
    public void canChangeEnrollmentLimitOnceStudentsRegister(){
        assertTrue(basketWeaving101.setEnrollmentLimit(10));
        fred.enrollInCourse(basketWeaving101);
        assertTrue(basketWeaving101.setEnrollmentLimit(8));
    }

    @Test
    public void cannotChangeEnrollmentLimitToLessThanNumStudents(){
        sally.enrollInCourse(basketWeaving101);
        fred.enrollInCourse(basketWeaving101);
        assertFalse(basketWeaving101.setEnrollmentLimit(1));
    }

    @Test
    public void canMakeEnrollmentUnlimited() {
        basketWeaving101.setEnrollmentLimit(-5);
        assertEquals(-1, basketWeaving101.getEnrollmentLimit());
        assertTrue(basketWeaving101.spaceAvailable());
    }

    @Test
    public void addsStudentsFromWaitlistIfCourseHasSpace() {
        basketWeaving101.setEnrollmentLimit(1);
        sally.enrollInCourse(basketWeaving101);
        fred.enrollInCourse(basketWeaving101);
        zongo.enrollInCourse(basketWeaving101);
        assertFalse(basketWeaving101.spaceAvailable());
        assertEquals(1, basketWeaving101.getEnrolledStudents().size());
        assertEquals(2, basketWeaving101.getWaitList().size());
        basketWeaving101.setEnrollmentLimit(-1);
        assertTrue(basketWeaving101.spaceAvailable());
        assertEquals(3, basketWeaving101.getEnrolledStudents().size());
        assertEquals(0, basketWeaving101.getWaitList().size());
    }

    // ------ Drop courses ------

    @Test
    public void studentCanDrop() {
        sally.enrollInCourse(comp225);
        sally.dropCourse(comp225);
        assertEquals(set(), sally.getCourses());
        assertEquals(set(), comp225.getEnrolledStudents());
    }

    @Test
    public void dropHasNoEffectOnOtherCoursesOrStudents() {
        sally.enrollInCourse(comp225);
        fred.enrollInCourse(comp225);
        sally.enrollInCourse(math6);
        sally.dropCourse(comp225);
        assertEquals(set(math6), sally.getCourses());
        assertEquals(set(fred), comp225.getEnrolledStudents());
    }

    @Test
    public void dropRemovesFromWaitList() {
        factory.enrollMultipleStudents(comp225, 16);
        sally.enrollInCourse(comp225);
        fred.enrollInCourse(comp225);
        zongo.enrollInCourse(comp225);
        fred.dropCourse(comp225);
        assertEquals(list(sally, zongo), comp225.getWaitList());
    }

    @Test
    public void dropEnrollsWaitListedStudents() {
        sally.enrollInCourse(comp225);
        factory.enrollMultipleStudents(comp225, 15);
        zongo.enrollInCourse(comp225);
        fred.enrollInCourse(comp225);
        sally.dropCourse(comp225);
        assertTrue(comp225.getEnrolledStudents().contains(zongo));
        assertEquals(list(fred), comp225.getWaitList());
    }

    // ------ Post-test invariant check ------
    //
    // This is a bit persnickety for day-to-day testing, but these kinds of checks are appropriate
    // for security sensitive or otherwise mission critical code. Some people even add them as
    // runtime checks in the code, instead of writing them as tests.

    @After
    public void checkInvariants() {
        for(Student s : factory.allStudents())
            checkStudentInvariants(s);
        for(Course c : factory.allCourses())
            checkCourseInvariants(c);
    }

    private void checkStudentInvariants(Student s) {
        for(Course c : s.getCourses())
            assertTrue(
                    s + " thinks they are enrolled in " + c + ", but " + c + " does not have them in the list of students",
                    c.getEnrolledStudents().contains(s));
    }

    private void checkCourseInvariants(Course c) {
        Set<Student> waitListUnique = new HashSet<Student>(c.getWaitList());
        assertEquals(
                c + " wait list contains duplicates: " + c.getWaitList(),
                waitListUnique.size(),
                c.getWaitList().size());

        waitListUnique.retainAll(c.getEnrolledStudents());
        assertEquals(
                c + " contains students who are both registered and waitlisted",
                Collections.emptySet(),
                waitListUnique);

        for(Student s : c.getEnrolledStudents())
            assertTrue(
                    c + " thinks " + s + " is enrolled, but " + s + " doesn't think they're in the class",
                    s.getCourses().contains(c));

        for(Student s : c.getWaitList())
            assertFalse(
                    c + " lists " + s + " as waitlisted, but " + s + " thinks they are enrolled",
                    s.getCourses().contains(c));

        assertTrue(
                c + " has an enrollment limit of " + c.getEnrollmentLimit()
                        + ", but has " + c.getEnrolledStudents().size() + " students",
                (c.getEnrolledStudents().size() <= c.getEnrollmentLimit()) || c.getEnrollmentLimit() == -1);

        if(c.getEnrolledStudents().size() < c.getEnrollmentLimit())
            assertEquals(
                    c + " is not full, but has students waitlisted",
                    Collections.emptyList(),
                    c.getWaitList());
    }

    // ------ Helpers ------

    private static <T> Set<T> set(T... args) {
        return new HashSet<T>(Arrays.asList(args));
    }

    private static <T> List<T> list(T... args) {
        return Arrays.asList(args);
    }
}