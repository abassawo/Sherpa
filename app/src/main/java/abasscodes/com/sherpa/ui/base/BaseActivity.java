package abasscodes.com.sherpa.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.support.v7.app.AppCompatActivity;

import abasscodes.com.sherpa.SherpaApplication;
import abasscodes.com.sherpa.data.BookRepositoryComponent;

public class BaseActivity extends AppCompatActivity {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    protected BookRepositoryComponent getBookRepositoryComponent() {
        return ((SherpaApplication) getApplication()).getRepositoryComponent();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}