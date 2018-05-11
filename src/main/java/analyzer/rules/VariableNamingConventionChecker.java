package analyzer.rules;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

public class VariableNamingConventionChecker extends AbstractVoidVisitorAdapter {

    private static final String CLASS_NAME_REGEX = "([A-Z][a-z0-9]+)+";

    private static final String METHOD_NAME_REGEX = "^[a-z][a-zA-Z0-9]*$";

    private static final String CONST_REGEX = "/(([A-Z]+_[A-Z]*)+)/gx";

    private String methodName;

    private String className = "Undefined";

    @Override
    public void visit(CompilationUnit compilationUnit, Collector collector) {

        if (compilationUnit.getTypes().size() > 0) {
            className = compilationUnit.getTypes().get(0).getNameAsString();
        }
        super.visit(compilationUnit, collector);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Collector collector) {

        if (declaration.getNameAsString().length() > 2 &&
                !declaration.getNameAsString().matches(CLASS_NAME_REGEX)) {
            collector.addWarning(className, "Class name \"" + className + "\" must be in CamelCase");
        }
        super.visit(declaration, collector);
    }


    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {

        methodName = declaration.getNameAsString();
        if (methodName.contains("_") || !methodName.matches(METHOD_NAME_REGEX)) {
            collector.addWarning(className, "Method \"" + className + ":"
                    + methodName + "\" not in 'camelCase'");
        }

        for (Parameter param : declaration.getParameters()) {
            if (param.getNameAsString().contains("_")) {
                collector.addWarning(className, "In " + className + ":" + methodName + " variable \""
                        + param.getNameAsString() + "\" not in 'camelCase'");
            }
        }

        super.visit(declaration, collector);
    }

    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {

        for (VariableDeclarator variable : declaration.getVariables()) {
            String name = variable.getNameAsString();
            if (name.matches(CONST_REGEX))
                continue;
            if (name.contains("_")) {
                collector.addWarning(className, "In " + className + ":" + methodName +
                        " variable \"" + name + "\" not in 'camelCase'");
            }
        }
    }
}
