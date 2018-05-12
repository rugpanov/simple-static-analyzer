package analyzer;

import analyzer.collectors.Collector;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.google.inject.Inject;

import java.io.FileNotFoundException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.Set;

public class Analyzer extends SimpleFileVisitor<Path> {

    private Collector collector;
    private Collection<BaseAdapter> visitors;

    @Inject
    public Analyzer(Collector collector, Set<BaseAdapter> visitors) {
        this.collector = collector;
        this.visitors = visitors;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws FileNotFoundException {
        if (!isJava(file))
            return FileVisitResult.CONTINUE;
        try {
            CompilationUnit unit = JavaParser.parse(file.toFile());
            for (BaseAdapter visitor: visitors) {
                visitor.visit(unit, collector);
            }
        } catch (ParseProblemException err) {
            collector.addWarning(extractClassName(file), "Method cannot be 'abstract' and also 'private', 'static'!!!");
        }
        return FileVisitResult.CONTINUE;
    }

    private boolean isJava(Path file) {
        return file.getFileName().toString().endsWith("java");
    }

    private String extractClassName(Path file) {

        String filename = file.getFileName().toString();

        if (filename.indexOf(".") > 0) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }
        return filename;
    }
}
