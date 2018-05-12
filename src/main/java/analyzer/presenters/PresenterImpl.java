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
            System.out.println("No warnings!");
        } else if (warnCounter == 1) {
            System.out.println("In the project there is 1 warning!");
        } else {
            System.out.println("In the project there are " + warnCounter + " warnings!");
        }

        if (infoCounter == 0) {
            System.out.println("No warnings!");
        } else if (infoCounter == 1) {
            System.out.println("In the project there is 1 info!");
        } else {
            System.out.println("In the project there are " + infoCounter + " info!");
        }


        for (Map.Entry<String, List<String>> entry : collector.getWarnings().entrySet()) {
            System.out.println(entry.getKey() + " class has " + entry.getValue().size() + " warnings");
            for (String warning : entry.getValue()) {
                System.out.println("[WARNING] " + warning);
            }
            System.out.println();
        }

        for (Map.Entry<String, List<String>> entry : collector.getInfo().entrySet()) {
            System.out.println(entry.getKey() + " class has " + entry.getValue().size() + " infos");
            for (String info : entry.getValue()) {
                System.out.println("[INFO] " + info);
            }
            System.out.println();
        }
    }
}
