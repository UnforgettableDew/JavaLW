import utility.FileRewriter;
import utility.FileScanner;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadingLW1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        File file = new File("D:/GG");
        System.out.println(file.exists());
        Future<String> stringFuture = executorService.submit(new FileRewriter(executorService, file, new FileScanner()));
        System.out.println(stringFuture.get());
        executorService.shutdown();
    }
}
