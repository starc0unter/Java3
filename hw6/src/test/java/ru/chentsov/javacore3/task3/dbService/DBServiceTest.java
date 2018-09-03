package ru.chentsov.javacore3.task3.dbService;

import org.junit.*;
import ru.chentsov.javacore3.task3.dbService.dataset.StudentDataSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public class DBServiceTest {

    private static final DBService testDBService = new DBService("jdbc:h2:./h2db-test", "name", "name");
    private static final Path TEST_DATA_PATH = Paths.get("src\\test\\java\\ru\\chentsov\\javacore3\\task3\\dbService\\testData");

    private List<String> testList;

    @Before
    public void prepareDB() throws IOException {
        testList = Files.readAllLines(TEST_DATA_PATH);
        for (String testRecord : testList) {
            String[] tokens = testRecord.split(" ");
            String lastName = tokens[0];
            int grade = Integer.parseInt(tokens[1]);
            testDBService.addStudent(lastName, grade);
        }
    }

    @Test
    public void get() {
        new HashSet<>(testList).stream()
            .map(s -> s.split(" ")[0])
            .map(testDBService::get)
            .flatMap(Collection::stream)
            .map(data -> data.getLastName() + " " + data.getGrade())
            .forEach(line -> Assert.assertTrue("Extracted line : \n" + line, testList.contains(line)));
    }

    @Test
    public void updateStudentGrade() {
        int newGrade = 1;

        //updating grades of all students to 1
        new HashSet<>(testList).stream()
                .map(s -> s.split(" ")[0])
                .forEach(lastName -> testDBService.updateStudentGrade(lastName, newGrade));

        //asserting that every grade in DB now equals newGrade
        new HashSet<>(testList).stream()
                .map(s -> s.split(" ")[0])
                .map(testDBService::get)
                .flatMap(Collection::stream)
                .map(StudentDataSet::getGrade)
                .forEach(grade -> Assert.assertEquals("Expected grade is "
                        + newGrade + ", real grade is " + grade, (long) newGrade, (long) grade));
    }

    @After
    public void removeDB() {
        testDBService.cleanDB();
    }

}