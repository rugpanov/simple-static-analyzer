package analyzer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public abstract class AbstractVoidVisitorAdapter<A> extends VoidVisitorAdapter<A> {

    @Override
    public void visit(CompilationUnit cu, A arg) {
        super.visit(cu, arg);
    }
}
