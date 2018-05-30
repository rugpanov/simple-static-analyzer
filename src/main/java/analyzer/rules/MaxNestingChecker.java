package analyzer.rules;

import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithBlockStmt;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.stmt.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MaxNestingChecker extends BaseAdapter {
    private static final int MAX_NESTING_LEVEL = 3;

    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        if (!declaration.getBody().isPresent()) return;

        int nesting = getNesting(declaration.getBody().get());
        if (nesting <= MAX_NESTING_LEVEL) return;

        collector.addWarning(className, "Nesting level is too high " + "(" + Integer.toString(nesting) + ") in "
                + className + ":" + declaration.getNameAsString() + ".");
    }

    private int getNesting(Statement stmt) {
        return getNesting(stmt, false);
    }

    private int getNesting(Statement stmt, boolean countBlock) {
        if (stmt instanceof BlockStmt) {
            return (countBlock ? 1 : 0) + getMaxStreamNesting(((BlockStmt) stmt).getStatements().stream(), true);
        }

        if (stmt instanceof NodeWithBody) return 1 + getNesting(((NodeWithBody) stmt).getBody());

        if (stmt instanceof NodeWithBlockStmt) return 1 + getNesting(((NodeWithBlockStmt) stmt).getBody());

        if (stmt instanceof IfStmt) {
            IfStmt ifStmt = (IfStmt) stmt;
            return 1 + Math.max(
                    getNesting(ifStmt.getThenStmt()),
                    ifStmt.getElseStmt().map(this::getNesting).orElse(0)
            );
        }

        if (stmt instanceof TryStmt) {
            TryStmt tryStmt = (TryStmt) stmt;
            return 1 + IntStream.of(
                    getNesting(tryStmt.getTryBlock()),
                    getMaxStreamNesting(tryStmt.getCatchClauses().stream().map(CatchClause::getBody), false),
                    tryStmt.getFinallyBlock().map(this::getNesting).orElse(0)
            ).max().getAsInt();
        }

        if (stmt instanceof SwitchStmt) {
            return 1 + getMaxStreamNesting(
                    ((SwitchStmt) stmt).getEntries()
                            .stream()
                            .map(SwitchEntryStmt::getStatements)
                            .flatMap(Collection::stream),
                    false
            );
        }

        return 0;
    }

    private int getMaxStreamNesting(Stream<Statement> stream, final boolean countBlock) {
        return stream.map(stmt -> getNesting(stmt, countBlock)).max(Comparator.comparingInt(id -> id)).orElse(0);
    }
}
