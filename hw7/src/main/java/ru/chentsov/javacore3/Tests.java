package ru.chentsov.javacore3;

import ru.chentsov.javacore3.tester.annotations.AfterSuite;
import ru.chentsov.javacore3.tester.annotations.BeforeSuite;
import ru.chentsov.javacore3.tester.annotations.Test;

/**
 * @author Evgenii Chentsov
 */
public class Tests {

    @BeforeSuite
    public void prepare() {
        System.out.println("making preparations");
    }

    @Test(priority = Test.Priority.VERY_LOW)
    public void test1() {
        System.out.println("In test 1");
    }

    @Test(priority = Test.Priority.LOW)
    public void test2() {
        System.out.println("In test 2");
    }

    @Test(priority = Test.Priority.MAX_PRIORITY)
    public void test3() {
        System.out.println("In test 3");
    }

    @Test(priority = Test.Priority.HIGHEST)
    public void test4() {
        System.out.println("In test 4");
    }

    @Test
    public void test5() {
        System.out.println("In test 5");
    }

    @AfterSuite
    public void cleanUp() {
        System.out.println("making cleanup");
    }

}
