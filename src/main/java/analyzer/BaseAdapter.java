package analyzer;

import analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public abstract class BaseAdapter extends VoidVisitorAdapter<Collector> {

    protected String className = "Undefined";

    @Override
    public void visit(CompilationUnit compilationUnit, Collector collector) {
        if (compilationUnit.getTypes().size() > 0) {
            className = compilationUnit.getTypes().get(0).getNameAsString();
        }
        super.visit(compilationUnit, collector);
    }
}
