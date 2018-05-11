package analyzer.rules;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

public class VariableNameLengthChecker extends AbstractVoidVisitorAdapter {
    private String className = "Undefined";

    @Override
    public void visit(CompilationUnit compilationUnit, Collector collector) {
        if (compilationUnit.getTypes().size() > 0) {
            className = compilationUnit.getTypes().get(0).getNameAsString();
        }
        super.visit(compilationUnit, collector);
    }

    private static final int MIN_VARIABLE_LENGTH = 3;
    private static final int MAX_VARIABLE_LENGTH = 20;

    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {

        declaration.getVariables()
                .stream()
                .filter(x -> x.getNameAsString().length() < MIN_VARIABLE_LENGTH)
                .forEach(x -> collector.addWarning(className,
                        "Variable name \"" + x.getNameAsString() + "\" has too small name"));

        declaration.getVariables()
                .stream()
                .filter(x -> x.getNameAsString().length() > MAX_VARIABLE_LENGTH)
                .forEach(x -> collector.addWarning(className,
                        "Variable name \"" + x.getNameAsString() + "\" has too long name"));
    }
}