import analyzer.Analyzer;
import analyzer.collectors.Collector;
import analyzer.presenters.Presenter;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar analyzer [path]");
        } else {
            String path = args[0];
            walkThroughFiles(Paths.get(path));
        }
    }

    private static void walkThroughFiles(Path path) {
        try {
            Injector injector = Guice.createInjector(new DiConfigModule());
            Analyzer analyzer = injector.getInstance(Analyzer.class);
            Presenter presenter = injector.getInstance(Presenter.class);
            Files.walkFileTree(path, analyzer);

            Collector collector = injector.getInstance(Collector.class);

            presenter.present(collector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
