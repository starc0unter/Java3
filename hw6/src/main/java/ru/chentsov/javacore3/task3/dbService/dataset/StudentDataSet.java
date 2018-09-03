package ru.chentsov.javacore3.task3.dbService.dataset;

/**
 * @author Evgenii Chentsov
 */
public class StudentDataSet {

    private long id;
    private String lastName;
    private int grade;

    public StudentDataSet(long id, String lastName, int grade) {
        this.id = id;
        this.lastName = lastName;
        this.grade = grade;
    }

    @SuppressWarnings("unused")
    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "StudentSet: " +
                "id = " + id
                + ", lastName = " + lastName
                + ", grade = " + grade;
    }

}
