package analyzer.rules;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;

public class ClassLineChecker extends AbstractVoidVisitorAdapter {
    private static final int MAX_CLASS_LENGTH = 250;

    public void visit(CompilationUnit compilationUnit, Collector collector) {
        String className = "Undefined";
        if (compilationUnit.getTypes().size() > 0) {
            className = compilationUnit.getTypes().get(0).getNameAsString();
        }

        int count = compilationUnit.toString().split("\n").length;
        if (count > MAX_CLASS_LENGTH) {
            collector.addWarning(className, "Class has more than " + MAX_CLASS_LENGTH + " lines");
        }

        super.visit(compilationUnit, collector);
    }

}
