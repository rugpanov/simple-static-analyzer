import analyzer.BaseAdapter;
import analyzer.collectors.Collector;
import analyzer.collectors.SimpleCollector;
import analyzer.presenters.Presenter;
import analyzer.presenters.PresenterImpl;
import analyzer.rules.*;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

public class DiConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<BaseAdapter> visitorBinder = Multibinder.newSetBinder(binder(), BaseAdapter.class);
        visitorBinder.addBinding().to(BooleanMethodNamingChecker.class);
        visitorBinder.addBinding().to(ClassLineChecker.class);
        visitorBinder.addBinding().to(NamingConventionChecker.class);
        visitorBinder.addBinding().to(VariableNameLengthChecker.class);
        visitorBinder.addBinding().to(IncompatibleDeclarationsCheck.class);

        visitorBinder.addBinding().to(MaxNestingChecker.class);
        visitorBinder.addBinding().to(UnusedVariablesChecker.class);
        visitorBinder.addBinding().to(SameIfBranchesChecker.class);
        visitorBinder.addBinding().to(ConstantConditionChecker.class);

        bind(Collector.class).to(SimpleCollector.class).in(Singleton.class);
        bind(Presenter.class).to(PresenterImpl.class).in(Singleton.class);
    }
}