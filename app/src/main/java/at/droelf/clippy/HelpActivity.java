package at.droelf.clippy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.zendesk.sdk.feedback.ZendeskFeedbackConfiguration;
import com.zendesk.sdk.model.CustomField;
import com.zendesk.sdk.model.DeviceInfo;
import com.zendesk.sdk.network.impl.ZendeskConfig;
import com.zendesk.sdk.storage.SdkStorage;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import at.droelf.clippy.fragments.FeedBackHelpFragment;
import at.droelf.clippy.fragments.UserHelpFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

public class HelpActivity extends AppCompatActivity {

    private ScreenState currentScreen;

    @InjectView(R.id.help_fragemnt)
    FrameLayout fragmentContainer;

    @InjectView(R.id.help_toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        initSdk();

        if(SdkStorage.INSTANCE.identity().getIdentity() == null){
            currentScreen = ScreenState.UserRegistration;
        }else{
            currentScreen = ScreenState.FeedBack;
        }

        showFragment(currentScreen);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initSdk(){

        final DeviceInfo deviceInfo = new DeviceInfo(this);

        final String deviceModel = String.format(
                Locale.US,
                "%s, %s, %s",
                deviceInfo.getModelName(), deviceInfo.getModelDeviceName(), deviceInfo.getModelManufacturer()
        );

        final String osVersion = String.format(
                Locale.US,
                "Android %s, Version %s",
                deviceInfo.getVersionName(), deviceInfo.getVersionCode()
        );

        final String appVersion = String.format(
                Locale.US,
                "version_%s",
                BuildConfig.VERSION_NAME
        );

        ZendeskConfig.INSTANCE.setContactConfiguration(new ZendeskFeedbackConfiguration() {
            @Override
            public List<String> getTags() {
                return Arrays.asList("clippy", "android");
            }

            @Override
            public String getAdditionalInfo() { return null; }

            @Override
            public String getRequestSubject() {
                return "Feedback - Ticket";
            }
        });

        final List<CustomField> customFields = Arrays.asList(
                new CustomField(26579771l, deviceModel),
                new CustomField(26600312l, osVersion),
                new CustomField(26600322l, appVersion)
        );

        ZendeskConfig.INSTANCE.setCustomFields(customFields);

    }


    private void showFragment(ScreenState screenState){
        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        final Fragment fragment = supportFragmentManager.findFragmentByTag(screenState.getTag());

        if(!(fragment != null && fragment.getClass().isInstance(screenState.getFragment()))){
            Timber.d("Create a new Helpfragment: %s", screenState);
            final FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.help_fragemnt, screenState.getFragment());
            fragmentTransaction.commit();
        }
    }

    public void next() {
        if(currentScreen == ScreenState.UserRegistration){
            this.currentScreen = ScreenState.FeedBack;
            showFragment(currentScreen);
        }
    }


    enum ScreenState{

        UserRegistration(UserHelpFragment.newInstance(), "fragment_user_reg"),
        FeedBack(FeedBackHelpFragment.newInstance(), "fragment_feedback");

        private final Fragment fragment;
        private final String tag;

        ScreenState(Fragment fragment, String tag){
            this.fragment = fragment;
            this.tag = tag;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public String getTag() {
            return tag;
        }
    }



}
