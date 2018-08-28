package ru.chentsov.javacore3;

/**
 * @author Evgenii Chentsov
 */
class MFP {

    private static final Object printLock = new Object();
    private static final Object scanLock = new Object();

    void print(String operator, int pagesCount) {
        synchronized (printLock) {
            for (int i = 0; i < pagesCount; i++) {
                System.out.println(operator + " printed " + (i + 1) + " pages");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void scan(String operator, int pagesCount) {
        synchronized (scanLock) {
            for (int i = 0; i < pagesCount; i++) {
                System.out.println(operator + " scanned " + (i + 1) + " pages");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
