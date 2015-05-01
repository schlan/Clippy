package at.droelf.clippy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
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

    @InjectView(R.id.help_user_reg_fab)
    FloatingActionButton fab;

    public static UserHelpFragment newInstance(){
        final UserHelpFragment userHelpFragment = new UserHelpFragment();
        userHelpFragment.setRetainInstance(true);
        return userHelpFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_help_user_registration, container, false);
        ButterKnife.inject(this, v);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validEmail = emailEditText.validateWith(new RegexpValidator("Invalid email", Patterns.EMAIL_ADDRESS.pattern()));
                boolean validName = userNameEditText.validateWith(new METValidator("Name too short") {
                    @Override
                    public boolean isValid(CharSequence charSequence, boolean b) {
                        return charSequence != null && charSequence.length() > 2;
                    }
                });

                if (validEmail && validName) {
                    Toast.makeText(getActivity(), "Valid -> next", Toast.LENGTH_LONG).show();
                    final Identity build = new AnonymousIdentity.Builder().withNameIdentifier(userNameEditText.getText().toString()).withEmailIdentifier(emailEditText.getText().toString()).build();
                    ZendeskConfig.INSTANCE.setIdentity(build);
                    if (getActivity() != null) {
                        ((HelpActivity) getActivity()).next();
                    }
                }
            }
        });

        return v;
    }
}
