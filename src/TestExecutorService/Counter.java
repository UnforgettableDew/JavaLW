package TestExecutorService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Counter implements Callable<Integer> {
    private File file;
    private ExecutorService executorService;

    public Counter(File file, ExecutorService executorService) {
        this.file = file;
        this.executorService = executorService;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        File[] files = file.listFiles();
        List<Future<Integer>> futureList = new ArrayList<>();
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println(f.getName() + " is directory");
                //Counter counter = new Counter(f, executorService);
                Future<Integer> intermediateResult = executorService.submit(new Counter(f, executorService));
                futureList.add(intermediateResult);
            } else {
                count++;
                System.out.println(f.getName() + " " + Thread.currentThread().getName());
            }
        }
        for (Future<Integer> intermediateResult : futureList) {
            count += intermediateResult.get();
        }
        System.out.println(count + " " + Thread.currentThread().getName());
        return count;
    }
}
