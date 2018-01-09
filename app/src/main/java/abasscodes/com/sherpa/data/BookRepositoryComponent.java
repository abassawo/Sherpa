package abasscodes.com.sherpa.data;

import javax.inject.Singleton;

import abasscodes.com.sherpa.AppModule;
import abasscodes.com.sherpa.data.model.ApiServiceModule;
import abasscodes.com.sherpa.data.repository.BookRepository;
import dagger.Component;

@Singleton
@Component(modules = { BookRepositoryModule.class, AppModule.class, ApiServiceModule.class, DatabaseModule.class})
public interface BookRepositoryComponent {
    BookRepository provideRepository();
}
