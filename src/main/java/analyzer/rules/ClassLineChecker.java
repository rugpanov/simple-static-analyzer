package analyzer.rules;

import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;

public class ClassLineChecker extends BaseAdapter {
    private static final int MAX_CLASS_LENGTH = 250;

    public void visit(CompilationUnit compilationUnit, Collector collector) {
        int count = compilationUnit.toString().split("\n").length;
        if (count > MAX_CLASS_LENGTH) {
            collector.addWarning(className, "Class has more than " + MAX_CLASS_LENGTH + " lines");
        }

        super.visit(compilationUnit, collector);
    }

}
