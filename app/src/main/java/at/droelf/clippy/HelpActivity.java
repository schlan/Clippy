package at.droelf.clippy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.melnykov.fab.FloatingActionButton;
import com.zendesk.sdk.feedback.ZendeskFeedbackConfiguration;
import com.zendesk.sdk.network.impl.ZendeskConfig;
import com.zendesk.sdk.storage.SdkStorage;

import java.util.Arrays;
import java.util.List;

import at.droelf.clippy.fragments.FeedBackHelpFragment;
import at.droelf.clippy.fragments.UserHelpFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class HelpActivity extends AppCompatActivity {

    private ScreenState currentScreen;

    @InjectView(R.id.help_fab)
    FloatingActionButton floatingActionButton;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    public FloatingActionButton getFab(){
        return floatingActionButton;
    }

    private void initSdk(){
        ZendeskConfig.INSTANCE.init(this, "https://clippy.zendesk.com", "c4c73bee174db74b69c8057010b859141b6093eb439182ea", "mobile_sdk_client_0ad88c1b1ed31aadc633");
        ZendeskConfig.INSTANCE.setContactConfiguration(new ZendeskFeedbackConfiguration() {
            @Override
            public List<String> getTags() {
                return Arrays.asList("clippy", "android");
            }

            @Override
            public String getAdditionalInfo() {
                return "";
            }

            @Override
            public String getRequestSubject() {
                return "I'm your biggest fan";
            }
        });
    }


    private void showFragment(ScreenState screenState){
        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        final Fragment fragment = supportFragmentManager.findFragmentByTag(screenState.getTag());

        if(!(fragment != null && fragment.getClass().isInstance(screenState.getFragment()))){
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

        UserRegistration(UserHelpFragment.newInstance(), "fragment_userreg"),
        FeedBack(FeedBackHelpFragment.newInstance(), "fragment_feedback");

        private Fragment fragment;
        private String tag;

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
