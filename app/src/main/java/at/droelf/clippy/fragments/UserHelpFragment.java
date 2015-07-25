package at.droelf.clippy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;
import com.rengwuxian.materialedittext.validation.RegexpValidator;
import com.zendesk.sdk.model.network.AnonymousIdentity;
import com.zendesk.sdk.model.network.Identity;
import com.zendesk.sdk.network.impl.ZendeskConfig;

import at.droelf.clippy.HelpActivity;
import at.droelf.clippy.R;
import butterknife.ButterKnife;
import butterknife.Bind;

public class UserHelpFragment extends Fragment{

    @Bind(R.id.help_user_name)
    MaterialEditText userNameEditText;

    @Bind(R.id.help_user_email)
    MaterialEditText emailEditText;

    @Bind(R.id.help_user_reg_fab)
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
        ButterKnife.bind(this, v);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validEmail = emailEditText.validateWith(new RegexpValidator(getString(R.string.support_textview_email_error), Patterns.EMAIL_ADDRESS.pattern()));
                boolean validName = userNameEditText.validateWith(new METValidator(getString(R.string.support_textview_user_error)) {
                    @Override
                    public boolean isValid(@NonNull CharSequence charSequence, boolean b) {
                        return charSequence.length() > 2;
                    }
                });

                if (validEmail && validName) {
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
