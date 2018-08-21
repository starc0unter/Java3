package ru.chentsov.javacore3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static ru.chentsov.javacore3.FilePopulation.populateFileSeries;
import static ru.chentsov.javacore3.FilePopulation.writeSmallData;

public class App
{
    public static void main( String[] args ) {
        //task 1
        File file50Bytes = new File("in50.txt");
        file50Bytes.deleteOnExit();
        String text50Bytes = "file1 file1 file1 file1 file1 file1 file1 file1 file1";
        writeSmallData(text50Bytes, file50Bytes);
        System.out.println("Reading bytes from 50-bytes file...");
        readSmallFile(file50Bytes);

        //task 2
        File[] filesToConcatenate = new File[5];
        populateFileSeries(filesToConcatenate);
        System.out.println("\nConcatenating files using SequenceInputStream...");
        concatenateFiles(new File("out100.txt"), filesToConcatenate);

        //task3
        File largeFile = new File("bigOne.txt");
        largeFile.deleteOnExit();
        FilePopulation.populateLargeFile(1800, 5600, largeFile);
        walkThroughDocument(largeFile, 1800);
    }

    private static void readSmallFile(File source) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
            int b;
            while ((b = in.read()) != -1) {
                System.out.print(b + " ");
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void concatenateFiles(File destination, File... sources) {
        destination.deleteOnExit();
        List<BufferedInputStream> fileSources = new ArrayList<>();
        try {
            for (File fis : sources) {
                fileSources.add(new BufferedInputStream(new FileInputStream(fis)));
            }

            try (   BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination));
                    SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(fileSources))) {
                int b;
                while ((b = sis.read()) != -1) {
                    out.write(b);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void walkThroughDocument(File source, int pageSize) {
        Scanner scanner = new Scanner(System.in);
        String currentLine;
        System.out.println("\nPlease enter the page you would like to see:");
        try (RandomAccessFile raf = new RandomAccessFile(source, "r")) {
            while (!"exit".equals(currentLine = scanner.nextLine())) {
                raf.seek(pageSize * (Integer.parseInt(currentLine) - 1));
                byte[] page = new byte[pageSize];
                raf.read(page);
                for (byte aPage : page) {
                    System.out.print((char) aPage);
                }
                System.out.println("\n Please enter the page you would like to see or type \"exit\" to exit:");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Wrong number, exiting...");
        } catch (IOException e) { e.printStackTrace(); }
    }

}
