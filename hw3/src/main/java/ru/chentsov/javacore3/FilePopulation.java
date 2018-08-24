package ru.chentsov.javacore3;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Evgenii Chentsov
 */
class FilePopulation {

    static void writeSmallData(String content, File destination) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
            out.write(content.getBytes());
            out.flush();
        } catch (IOException e) { e.printStackTrace(); }
    }

    static void populateLargeFile(int pageSize, long pageAmount, File destination) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
            for (int page = 0; page < pageAmount; page++) {
                StringBuilder builder = new StringBuilder();
                int currentFill = page % 10;
                for(int i = 0; i < pageSize; i++) {
                    builder.append(currentFill);
                }
                out.write(builder.toString().getBytes());
                out.flush();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    static void populateFileSeries(File[] filesToConcatenate) {
        for (int i = 0; i < filesToConcatenate.length; i++) {
            StringBuilder content = new StringBuilder();
            int phraseRepetitions = 19;
            for (int j = 0; j < phraseRepetitions; j++) {
                content.append("file").append(i + 1).append(" ");
            }

            filesToConcatenate[i] = new File("in100-" + (i + 1) + ".txt");
            filesToConcatenate[i].deleteOnExit();
            writeSmallData(content.toString(), filesToConcatenate[i]);
        }
    }

}
