package analyzer.collectors;

import java.util.HashMap;
import java.util.List;

public class SimpleCollector implements Collector {
    @Override
    public void addWarning(String className, String warning) {

    }

    @Override
    public void incrementMetric(String metricName) {

    }

    @Override
    public void incrementMetric(String metricName, int count) {

    }

    @Override
    public HashMap<String, List<String>> getWarnings() {
        return null;
    }

    @Override
    public HashMap<String, Integer> getStats() {
        return null;
    }
}
