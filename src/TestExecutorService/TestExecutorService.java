package TestExecutorService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class TestExecutorService {
    public static void main(String[] args) throws FileNotFoundException, ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        List<Future<String>> futureList = new ArrayList<>();
//        List<File> fileList = new ArrayList<>();
//
//        File file1 = new File("D:/GG/124353464urytghffds.txt");
//        File file2 = new File("D:/GG/adgfsgdfgh.txt");
//        fileList.add(file1);
//        fileList.add(file2);
//        FileReader fileReader = new FileReader();
//
//        for (int i = 0; i < fileList.size(); i++) {
//            int finalI = i;
//            futureList.add(executorService.submit(new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    return fileReader.readFile(fileList.get(finalI));
//                }
//            }));
//        }
//
//        for (Future<String> stringFuture : futureList) {
//            System.out.println(stringFuture.get());
//        }
        Counter counter = new Counter(new File("D:/GG"), executorService);
        Future<Integer> integerFuture = executorService.submit(counter);
        System.out.println(integerFuture.get());
        executorService.shutdown();
    }


}
