package ru.chentsov.javacore3;

import java.io.*;

public class App
{
    public static void main( String[] args )
    {
        try {
            task1();
            task2();
            task3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
     Используйте wait/notify/notifyAll.
     */
    private static void task1() throws InterruptedException{
            new Thread(() -> new CharPrinter().print("A")).start();
            new Thread(() -> new CharPrinter().print("B")).start();
            new Thread(() -> new CharPrinter().print("C")).start();
    }

    /*
    Написать небольшой метод, в котором 3 потока построчно пишут данные в файл (по 10 записей с периодом в 20 мс).
     */
    private static void task2() {
        File textStorage = new File("storage.txt");
        //textStorage.deleteOnExit();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(textStorage)))) {
            Thread[] threads = new Thread[3];

            threads[0] = new Thread(() -> writeData(writer, "AAAAA\n"));
            threads[1] = new Thread(() -> writeData(writer, "BBBBB\n"));
            threads[2] = new Thread(() -> writeData(writer, "CCCCC\n"));

            for (Thread t : threads) t.start();
            for (Thread t : threads) t.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    Написать класс МФУ, на котором возможно одновременно выполнять печать и сканирование документов, но нельзя
    одновременно печатать или сканировать два документа. При печати в консоль выводится сообщения «Отпечатано 1, 2,
    3,... страницы», при сканировании – аналогично «Отсканировано...». Вывод в консоль с периодом в 50 мс.
     */
    private static void task3() {
        MFP mfp = new MFP();

        System.out.println();

        for (int i = 1; i < 5; i += 2) {
            int printWorker = i;
            int scanWorker = i + 1;
            new Thread(() -> mfp.print("Worker " + printWorker, 20)).start();
            new Thread(() -> mfp.scan("Worker " + scanWorker, 10)).start();
        }
    }

    private static void writeData(BufferedWriter writer, String text) {
        try {
            for (int i = 0; i < 10; i++) {
                writer.write(text, 0, text.length());
                Thread.sleep(20);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
