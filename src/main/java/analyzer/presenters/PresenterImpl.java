package analyzer.presenters;

import analyzer.collectors.Collector;

import java.util.List;
import java.util.Map;

public class PresenterImpl implements Presenter {

    @Override
    public void present(Collector collector) {
        int warnCounter = 0;

        for (Map.Entry<String, List<String>> entry : collector.getWarnings().entrySet()) {
            warnCounter += entry.getValue().size();
        }

        if (warnCounter == 0) {
            System.out.println("No warnings!");
        } else if (warnCounter == 1) {
            System.out.println("In the project there is 1 warning!");
        } else {
            System.out.println("In the project there is " + warnCounter + " warnings!");
        }

        for (Map.Entry<String, List<String>> entry : collector.getWarnings().entrySet()) {
            System.out.println(entry.getKey() + " class has " + entry.getValue().size() + " warnings");
            for (String warning : entry.getValue()) {
                System.out.println("[WARNING] " + warning);
            }
            System.out.println();
        }
    }
}
