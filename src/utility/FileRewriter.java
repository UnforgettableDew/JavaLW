package utility;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class FileRewriter implements Callable<String> {
    ExecutorService executorService;
    File file;
    FileScanner fileScanner;

    public FileRewriter(ExecutorService executorService, File file, FileScanner fileScanner) {
        this.executorService = executorService;
        this.file = file;
        this.fileScanner = fileScanner;
    }

    @Override
    public String call() throws Exception {
        File[] files = file.listFiles();
        List<Future<String>> listOfFileNames = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    //System.out.println(f + " is directory");
                    //System.out.println(Thread.currentThread().getName());
                    Future<String> intermediateFileName =
                            executorService.submit(new FileRewriter(executorService, f, fileScanner));
                    listOfFileNames.add(intermediateFileName);
                } else if (f.getName().endsWith(".java")) {
                    result.append(f.getName()).append(" ").append(Thread.currentThread().getName()).append("\n");
                    //System.out.println(f + Thread.currentThread().getName());
                    String fileData = fileScanner.readFile(f);

                    FileWriter fileWriter = new FileWriter(f, false);
                    fileWriter.write(fileData);
                    fileWriter.flush();
                }
            }
            //System.out.println(result);
            for (Future<String> fileNames : listOfFileNames)
                result.append(fileNames.get());
        }
        return result.toString();
    }
}
