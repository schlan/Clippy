package at.droelf.clippy.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;
import com.rengwuxian.materialedittext.validation.RegexpValidator;
import com.zendesk.sdk.model.network.AnonymousIdentity;
import com.zendesk.sdk.model.network.Identity;
import com.zendesk.sdk.network.impl.ZendeskConfig;

import at.droelf.clippy.HelpActivity;
import at.droelf.clippy.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserHelpFragment extends Fragment{

    @InjectView(R.id.help_user_name)
    MaterialEditText userNameEditText;

    @InjectView(R.id.help_user_email)
    MaterialEditText emailEditText;


    public static UserHelpFragment newInstance(){
        return new UserHelpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_help_user_registration, container, false);
        ButterKnife.inject(this, v);
        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        final HelpActivity helpActivity = (HelpActivity) activity;
        final FloatingActionButton fab = helpActivity.getFab();
        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_forward));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validEmail = emailEditText.validateWith(new RegexpValidator("Invalid email", Patterns.EMAIL_ADDRESS.pattern()));
                boolean validName = userNameEditText.validateWith(new METValidator("Name too short") {
                    @Override
                    public boolean isValid(CharSequence charSequence, boolean b) {
                        return charSequence != null && charSequence.length() > 3;
                    }
                });

                if(validEmail && validName){
                    Toast.makeText(getActivity(), "Valid -> next", Toast.LENGTH_LONG).show();
                    Identity build = new AnonymousIdentity.Builder().withNameIdentifier(userNameEditText.getText().toString()).withEmailIdentifier(emailEditText.getText().toString()).build();
                    ZendeskConfig.INSTANCE.setIdentity(build);
                    helpActivity.next();
                }

            }
        });

    }
}
