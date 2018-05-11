package analyzer.rules;

import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.body.MethodDeclaration;

public class BooleanMethodNamingChecker extends BaseAdapter {

    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        if (!declaration.getType().toString().equals("boolean") ||
                declaration.getNameAsString().startsWith("is") ||
                declaration.getNameAsString().startsWith("are") ||
                declaration.getNameAsString().startsWith("should") ||
                declaration.getNameAsString().startsWith("has") ||
                declaration.getNameAsString().startsWith("can")) {
            return;
        }

        collector.addWarning(className, "Method name \"" + declaration.getNameAsString() +
                "\" should be like a question.");
    }
}