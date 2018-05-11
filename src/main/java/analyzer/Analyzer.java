package analyzer;

import analyzer.collectors.Collector;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.google.inject.Inject;

import java.io.FileNotFoundException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyzer extends SimpleFileVisitor<Path> {

    private Collector collector;
    private Collection<AbstractVoidVisitorAdapter> visitors;

    @Inject
    public Analyzer(Collector collector, Set<AbstractVoidVisitorAdapter> visitors) {
        this.collector = collector;
        this.visitors = visitors;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws FileNotFoundException {
        if (!isJava(file))
            return FileVisitResult.CONTINUE;

        CompilationUnit unit = JavaParser.parse(file.toFile());

        for (AbstractVoidVisitorAdapter visitor: visitors) {
            visitor.visit(unit, collector);
        }

        return FileVisitResult.CONTINUE;
    }

    private boolean isJava(Path file) {
        return file.getFileName().toString().endsWith("java");
    }
}
