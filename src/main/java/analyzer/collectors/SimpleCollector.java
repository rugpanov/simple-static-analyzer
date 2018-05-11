package analyzer.collectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleCollector implements Collector {

    private HashMap<String, List<String>> warnings;
    private HashMap<String, List<String>> info;

    public SimpleCollector() {
        warnings = new HashMap<>();
        info = new HashMap<>();
    }

    @Override
    public void addWarning(String className, String message) {
        warnings.putIfAbsent(className, new ArrayList<>());
        warnings.get(className).add(message);
    }

    @Override
    public void addInfo(String className, String message) {
        info.putIfAbsent(className, new ArrayList<>());
        info.get(className).add(message);
    }

    @Override
    public HashMap<String, List<String>> getWarnings() {
        return warnings;
    }

    @Override
    public HashMap<String, List<String>> getInfo() {
        return info;
    }
}