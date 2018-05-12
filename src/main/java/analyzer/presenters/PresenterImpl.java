package analyzer.presenters;

import analyzer.collectors.Collector;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class PresenterImpl implements Presenter {

    private final Collector collector;

    @Inject
    public PresenterImpl(Collector collector) {
        this.collector = collector;
    }

    @Override
    public void present() {
        int warnCounter = 0;
        int infoCounter = 0;

        for (Map.Entry<String, List<String>> entry : collector.getWarnings().entrySet()) {
            warnCounter += entry.getValue().size();
        }

        for (Map.Entry<String, List<String>> entry : collector.getInfo().entrySet()) {
            infoCounter += entry.getValue().size();
        }

        if (warnCounter == 0) {
            System.out.println("No warning problems!");
        } else if (warnCounter == 1) {
            System.out.println("In the project there is 1 warning problem!");
        } else {
            System.out.println("In the project there are " + warnCounter + " warning problems!");
        }

        if (infoCounter == 0) {
            System.out.println("No info problems!");
        } else if (infoCounter == 1) {
            System.out.println("In the project there is 1 info problem!");
        } else {
            System.out.println("In the project there are " + infoCounter + " info problems!");
        }

        System.out.println();
        for (Map.Entry<String, List<String>> entry : collector.getWarnings().entrySet()) {
            System.out.println(entry.getKey() + " class has " + entry.getValue().size() + " warning problems");
            for (String warning : entry.getValue()) {
                System.out.println("[WARNING] " + warning);
            }
            System.out.println();
        }

        for (Map.Entry<String, List<String>> entry : collector.getInfo().entrySet()) {
            System.out.println(entry.getKey() + " class has " + entry.getValue().size() + " info problems");
            for (String info : entry.getValue()) {
                System.out.println("[INFO] " + info);
            }
            System.out.println();
        }
    }
}
