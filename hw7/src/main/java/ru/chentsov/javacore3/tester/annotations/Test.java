package ru.chentsov.javacore3.tester.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    enum Priority { LOWEST, VERY_LOW, LOW, LOWER, MEDIUM, HIGHER, HIGH, VERY_HIGH, HIGHEST, MAX_PRIORITY }
    Priority priority() default Priority.MEDIUM;
}
