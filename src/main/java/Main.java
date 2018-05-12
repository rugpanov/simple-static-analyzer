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
            System.out.println("Usage: java -jar analyzer.jar [path to the project]");
        } else {
            String path = args[0];
            walkThroughFiles(Paths.get(path));
        }
    }

    private static void walkThroughFiles(Path path) {
        try {
            Injector injector = Guice.createInjector(new DiConfigModule());
            Analyzer analyzer = injector.getInstance(Analyzer.class);
            Files.walkFileTree(path, analyzer);

            Presenter presenter = injector.getInstance(Presenter.class);
            presenter.present();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
