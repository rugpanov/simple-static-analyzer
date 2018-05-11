package analyzer.collectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleCollector implements Collector {

    private HashMap<String, List<String>> warnings;

    @Override
    public void addWarning(String className, String warning) {
        warnings.putIfAbsent(className, new ArrayList<>());
        warnings.get(className).add(warning);
    }

    @Override
    public HashMap<String, List<String>> getWarnings() {
        return warnings;
    }
}