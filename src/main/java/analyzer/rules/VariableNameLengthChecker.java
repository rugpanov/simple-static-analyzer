package analyzer.rules;

import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

public class VariableNameLengthChecker extends BaseAdapter {
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