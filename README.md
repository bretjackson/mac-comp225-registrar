# Part 2: Adding new Features

This builds on Part 1.

It's almost time for registration. You've gotten the code more readable and easier to modify by refactoring. That's good because the registrar has come to you with new features to add:

* The registrar has decided that courses should not impose an enrollment limit by default.
* If an enrollment limit is already set, you should be able to remove it to allow unlimited enrollment.
* An existing enrollment limit should be modifiable at eny time, regardless of whether students have already started registering. (This actually make a lot of sense. Most of the time enrollment limits are changed when the class is already full and you want to add extra students.)

## Adding the features

Your task is to modify your Course class to implement the new features described above. 

In consultation with your technical lead, you decide that the public api to remove an enrollment limit and allow unlimited enrollment should be as follows:

```
public void removeEnrollmentLimit()
```

Add this method to your courses class and modify the implemention of other methods to implement the other new features (unlimited default enrollments, modifiable limits at any time).

## Adding new tests

Modifying 'RegistrarTest' to add new tests for the features you will add. Practice good defensive programming. You should think of all the possible ways that the program could get into an incorrect state based on your new features and test for these cases.

## Submitting your changes and tests

When you have implemented the new features and tests, make sure to commit and push to your fork on github. This will update your original pull request from part 1 to submit the full assignment.

**The assignment is due by 10am on Monday Feb. 29th**


The previous assignment appears below for reference.

-------------

# Part 1: A Refactoring Exercise

Oh no! The registrar recently contracted Programmer Bob's _CodingDoneCheapAndDirty, inc._ to build a new registration system for classes at Macalester. Unfortunately, Bob did a terrible job and the code is a mess! Registration time is in two weeks, and it's up to you to fix the system.

This assignment is an introduction to:

* **Refactoring**: changing the internal structure of software to make it easier to understand and cheaper to modify  without
changing its observable behavior.
* **Object modeling**: representing concepts as object-oriented code.
* **Programming by contract**: writing code that satisfies logical constraints.
* **Defensive programming**: writing code that actively prevents other code from using it incorrectly.

## Rules of the exercise

* **This is not a group assignment.** Please review the rules about collaboration vs. copying in the syllabus. ([Bret’s syllabus](https://moodle.macalester.edu/pluginfile.php/37166/mod_resource/content/2/SyllabusComp225SoftwareDesignDevelopmentSpring2016.pdf) | [Paul’s syllabus](https://moodle.macalester.edu/pluginfile.php/38330/mod_resource/content/1/Syllabus.pdf)).
* Use strict defensive programming: make it impossible to get any model object into an illegal state using public methods.

## Your starting point

Programmer Bob created two model classes to represent students and courses. The code in these classes smells pretty bad, but thankfully Bob's partner Alice wrote a collection of tests that you can use to help fix the code.

The original specification for the code has the following invariant conditions:

* Students should know their registered courses, and courses should know the list of students enrolled in them.
> For all students and courses, `student.getCourses().contains(course)` if and only if `course.getStudents().contains(student)`.

* Courses can have a max enrollment limit on the number of students. The enrollment limit cannot be changed once a student registers for the course.
> For all courses, `course.getStudents().size()` ≤ `course.getEnrollmentLimit()`.

* Courses can have a waitlist when they go over their enrollment limit. When a student attempts to register for a course that is full, they automatically go on the wait list. This is not an error condition; however, the `enroll()` method should let the caller know whether the enrollment was successful or the student was waitlisted.
> A student is never both enrolled in and wait listed for the same course.
> If a course is not full, then its wait list is empty.

* Students should be able to drop a course. If an enrolled student drops, then the first wait-listed student is automatically enrolled. (That’s not realistic, of course, but it makes for a better programming exercise!)

## Part 1

Your task is to refactor the Student and Course classes to make the code easier to read and modify in the future. The goal isn't to make
the code as _small_ as possible, but rather to make it as _clear_ and _simple_ as possible. Think carefully about the separation of concerns between the classes. You should also pay attention to variable names and rename them if it makes the code clearer. You do not need to modify the test classes.

In addition to refactoring, try to engage in defensive programming. You should identify and fix any gaps where it is possible to use the code incorrectly.

## Before you’re done…

…three things:

* Make sure that _all_ of the invariants listed above still hold, even after all of your changes.
* Make sure that you test with the `RegistrarTest` test scenario to satisfactorily demonstrate that it works.
* Practice good hygiene. Make sure your code is tidy, legible, and free of waste.
