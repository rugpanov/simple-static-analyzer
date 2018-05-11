package analyzer.rules;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

public class BooleanMethodNamingChecker extends AbstractVoidVisitorAdapter {

    private String className = "Undefined";

    @Override
    public void visit(CompilationUnit compilationUnit, Collector collector) {
        if (compilationUnit.getTypes().size() > 0) {
            className = compilationUnit.getTypes().get(0).getNameAsString();
        }
        super.visit(compilationUnit, collector);
    }

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