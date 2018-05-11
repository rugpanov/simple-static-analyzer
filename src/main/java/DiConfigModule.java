import analyzer.AbstractVoidVisitorAdapter;
import analyzer.collectors.Collector;
import analyzer.collectors.SimpleCollector;
import analyzer.rules.BooleanMethodNamingChecker;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

public class DiConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<AbstractVoidVisitorAdapter> visitorBinder =
                Multibinder.newSetBinder(binder(), AbstractVoidVisitorAdapter.class);
        visitorBinder.addBinding().to(BooleanMethodNamingChecker.class);

        bind(Collector.class).to(SimpleCollector.class).in(Singleton.class);
    }
}