package analyzer;

import analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public abstract class AbstractVoidVisitorAdapter extends VoidVisitorAdapter<Collector> {

    public void visit(final CompilationUnit compilationUnit, final Collector arg) {
        super.visit(compilationUnit, arg);
    }
}
