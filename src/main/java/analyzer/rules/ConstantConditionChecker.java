package analyzer.rules;

import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

public class ConstantConditionChecker extends BaseAdapter {
    @Override
    public void visit(IfStmt declaration, Collector collector) {
        Expression expr = declaration.getCondition();
        Boolean result = checkCondition(expr);

        if (result == null) return;

        addWarning(expr, result, collector);
    }

    @Override
    public void visit(WhileStmt declaration, Collector collector) {
        Expression expr = declaration.getCondition();
        Boolean result = checkCondition(expr);

        if (result == null || result) return;

        addWarning(expr, false, collector);
    }

    @Override
    public void visit(DoStmt declaration, Collector collector) {
        Expression expr = declaration.getCondition();
        Boolean result = checkCondition(expr);

        if (result == null || !result) return;

        addWarning(expr, true, collector);
    }

    private void addWarning(Expression expr, Boolean result, Collector collector) {
        collector.addWarning(className, getRangeString(expr) + "Condition is always " + result.toString() + ".");
    }

    private Boolean checkCondition(Expression expr) {
        if (expr.isBooleanLiteralExpr()) return expr.asBooleanLiteralExpr().getValue();
        if (expr.isBinaryExpr()) return checkCondition(expr.asBinaryExpr());
        if (expr.isEnclosedExpr()) return checkCondition(expr.asEnclosedExpr().getInner());

        if (expr.isUnaryExpr() && expr.asUnaryExpr().getOperator() == UnaryExpr.Operator.LOGICAL_COMPLEMENT) {
            Boolean result = checkCondition(expr.asUnaryExpr());
            if (result != null) result = !result;
            return result;
        }

        return null;
    }

    private Boolean checkCondition(BinaryExpr expr) {
        Boolean left = checkCondition(expr.getLeft());
        switch (expr.getOperator()) {
            case OR:
                return left == null || left ? left : checkCondition(expr.getRight());
            case AND:
                return left == null || !left ? left : checkCondition(expr.getRight());
        }
        return null;
    }
}
