package analyzer.rules;

import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.stmt.IfStmt;

public class SameIfBranchesChecker extends BaseAdapter {
    @Override
    public void visit(IfStmt stmt, Collector collector) {
        if (stmt.getElseStmt().isPresent() && stmt.getThenStmt().equals(stmt.getElseStmt().get())) {
            collector.addWarning(className, getRangeString(stmt)
                    + "'If' statement has same 'then' and 'else' branches.");
        }
    }
}
