package analyzer.presenters;

import analyzer.collectors.Collector;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresenterImpl implements Presenter {

    private class Problem {
        private String text;
        private String severity;

        Problem(String severity, String text) {
            this.text = text;
            this.severity = severity;
        }
    }

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

        HashMap<String, List<Problem>> map = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : collector.getWarnings().entrySet()) {
            List<Problem> problems = new ArrayList<>();
            for (String warning : entry.getValue()) {
                problems.add(new Problem("[WARNING]", warning));
            }
            map.put(entry.getKey(), problems);
        }

        for (Map.Entry<String, List<String>> entry : collector.getInfo().entrySet()) {
            List<Problem> problems = map.getOrDefault(entry.getKey(), new ArrayList<>());
            for (String text : entry.getValue()) {
                problems.add(new Problem("[INFO]", text));
            }
            map.put(entry.getKey(), problems);
        }

        System.out.println();
        for (Map.Entry<String, List<Problem>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " class has " + entry.getValue().size() + " problems");
            for (Problem problem : entry.getValue()) {
                System.out.println(problem.severity + " " + problem.text);
            }
            System.out.println();
        }
    }
}