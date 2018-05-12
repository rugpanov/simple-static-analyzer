package analyzer.rules;

import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class IncompatibleDeclarationsCheck extends BaseAdapter {

    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        String methodName = declaration.getNameAsString();
        if (declaration.isPrivate() && declaration.isFinal()) {
            collector.addWarning(className, "In " + className + ":" + methodName +
                    " has incorrect declarations: " + declaration.getDeclarationAsString() +
                    "\nAs private methods cannot be meaningfully overridden, declaring them final is redundant. ");
        }

        if (declaration.isStatic() && declaration.isFinal()) {
            collector.addWarning(className, "In " + className + ":" + methodName +
                    " has incorrect declarations: " + declaration.getDeclarationAsString() +
                    "\nDeclaring a static method final does prevent subclasses from defining a " +
                    "static method with the same signature.");
        }



        super.visit(declaration, collector);
    }

    @Override
    public void visit(final ConstructorDeclaration constructorDeclaration, final Collector collector) {
        if (constructorDeclaration.isPublic() && !classIsPublic) {
            collector.addWarning(className, "Non-public class \"" + className + "\" has public constructors.");
        }

        super.visit(constructorDeclaration, collector);
    }
}
