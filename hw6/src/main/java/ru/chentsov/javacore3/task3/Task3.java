package ru.chentsov.javacore3.task3;

import ru.chentsov.javacore3.task3.dbService.DBService;
import ru.chentsov.javacore3.task3.dbService.dataset.StudentDataSet;

import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public class Task3 {

    public static void main(String[] args) {
        DBService dbService = new DBService("jdbc:h2:./h2db", "name", "name");

        dbService.cleanDB();
        dbService.addStudentByBatch(100);
        dbService.addStudent("Ivan", 88);
        getGradeByStudentName(dbService, "student34");
    }

    private static void getGradeByStudentName(DBService dbService, String lastName) {
        List<StudentDataSet> rows = dbService.get(lastName);
        if(rows.isEmpty()) System.out.println("No such good exists");
        else {
            for(StudentDataSet data : rows) {
                System.out.println("Grade for " + data.getLastName() + " is " + data.getGrade());
            }
        }
    }

}
