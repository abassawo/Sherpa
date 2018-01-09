package abasscodes.com.sherpa.ui.main;

import abasscodes.com.sherpa.data.BookRepositoryComponent;
import abasscodes.com.sherpa.ui.base.ActivityScope;
import abasscodes.com.sherpa.util.schedulers.SchedulerModule;
import dagger.Component;

@ActivityScope
@Component(modules = {MainPresenterModule.class, SchedulerModule.class}, dependencies = {
        BookRepositoryComponent.class
})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}