package analyzer;

import analyzer.collectors.Collector;
import com.google.inject.Inject;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.Set;

public class Analyzer extends SimpleFileVisitor<Path> {

    private Collector collector;
    private Collection<AbstractVoidVisitorAdapter<Collector>> visitors;

    @Inject
    public Analyzer(Collector collector, Set<AbstractVoidVisitorAdapter<Collector>> visitors) {
        this.collector = collector;
        this.visitors = visitors;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

}
