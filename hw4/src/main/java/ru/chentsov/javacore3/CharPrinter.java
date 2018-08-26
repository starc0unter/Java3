package ru.chentsov.javacore3;

/**
 * @author Evgenii Chentsov
 */
class CharPrinter {
    private static volatile char next = 'A';
    private static final Object lock = new Object();

    void print(String letter) {
        int repetitions = 5;
        synchronized (lock) {
            for (int i = 1; i <= repetitions; i++) {
                while (next != letter.charAt(0)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(letter);
                next = (next == 'C') ? 'A' : (char) (letter.charAt(0) + 1);
                lock.notifyAll();
            }
        }
    }

}
