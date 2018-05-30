package analyzer.rules;

import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.NameExpr;

import java.util.HashSet;
import java.util.Set;

public class UnusedVariablesChecker extends BaseAdapter {
    private final Set<String> params = new HashSet<>();

    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        if (!declaration.getBody().isPresent() ||
                declaration.getParameters().isEmpty() ||
                "main".equals(declaration.getNameAsString())) {
            return;
        }

        params.clear();
        declaration.getParameters().forEach(param -> params.add(param.getNameAsString()));

        super.visit(declaration.getBody().get(), collector);

        for (String param : params) {
            collector.addWarning(className, className + ":" + declaration.getNameAsString()
                    + ": parameter " + param + " is unused.");
        }
    }

    @Override
    public void visit(NameExpr expr, Collector collector) {
        params.remove(expr.getNameAsString());
    }
}
