import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import analyzer.collectors.SimpleCollector;
import analyzer.rules.BooleanMethodNamingChecker;
import analyzer.rules.ClassLineChecker;
import analyzer.rules.VariableNameLengthChecker;
import analyzer.rules.VariableNamingConventionChecker;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

public class DiConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<BaseAdapter> visitorBinder = Multibinder.newSetBinder(binder(), BaseAdapter.class);
        visitorBinder.addBinding().to(BooleanMethodNamingChecker.class);
        visitorBinder.addBinding().to(ClassLineChecker.class);
        visitorBinder.addBinding().to(VariableNamingConventionChecker.class);
        visitorBinder.addBinding().to(VariableNameLengthChecker.class);

        bind(Collector.class).to(SimpleCollector.class).in(Singleton.class);
    }
}