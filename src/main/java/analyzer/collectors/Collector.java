package analyzer.collectors;

import java.util.HashMap;
import java.util.List;

public interface Collector {

    void addWarning(String className, String warning);

    HashMap<String, List<String>> getWarnings();
}
