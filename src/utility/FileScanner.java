package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class FileScanner {
    public String readFile(File input) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(input));
        StringBuilder result = new StringBuilder();
        while (sc.hasNextLine()) {
            String tmp = sc.nextLine();
            String[] line = tmp.split(" ");

            for (int i = 0; i < line.length; i++) {
                if (line[i].length() > 3)
                    line[i] = line[i].toUpperCase(Locale.ROOT);
                result.append(line[i]).append(" ");
            }
            result.deleteCharAt(result.length() - 1);
            result.append("\n");
        }
        if (!result.isEmpty())
            result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

}
