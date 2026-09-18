package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String LOG_FILE = "aura_log.txt";

    public static void log(String message) {

        try {

            FileWriter fileWriter =
                    new FileWriter(LOG_FILE, true);

            BufferedWriter writer =
                    new BufferedWriter(fileWriter);

            writer.write(message);
            writer.newLine();

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "AURA: Unable to write to log file."
            );
        }
    }
}
