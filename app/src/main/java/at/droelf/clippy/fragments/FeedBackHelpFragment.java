package at.droelf.clippy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;
import com.zendesk.sdk.feedback.impl.ZendeskFeedbackConnector;
import com.zendesk.sdk.model.CreateRequest;
import com.zendesk.sdk.network.impl.ZendeskConfig;
import com.zendesk.service.ErrorResponse;
import com.zendesk.service.SafeZendeskCallback;
import com.zendesk.service.ZendeskCallback;

import at.droelf.clippy.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class FeedBackHelpFragment extends Fragment {

    public static FeedBackHelpFragment newInstance(){
        final FeedBackHelpFragment feedBackHelpFragment = new FeedBackHelpFragment();
        feedBackHelpFragment.setRetainInstance(true);
        return feedBackHelpFragment;
    }

    @InjectView(R.id.help_user_feedback_fab)
    FloatingActionButton fab;

    @InjectView(R.id.help_user_feedback)
    MaterialEditText userFeedBackEditText;

    @InjectView(R.id.help_feedback_progress)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_help_feedback, container, false);
        ButterKnife.inject(this, inflate);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validName = userFeedBackEditText.validateWith(new METValidator(getString(R.string.support_textview_feedback_error)) {
                    @Override
                    public boolean isValid(@NonNull CharSequence charSequence, boolean b) {
                        return charSequence.length() > 1;
                    }
                });

                if (validName) {
                    sendComment(userFeedBackEditText.getText().toString());
                }
            }
        });

        return inflate;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        createRequestZendeskCallback.cancel();
    }

    private void setLoading(boolean loading){
        if(loading){
            userFeedBackEditText.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            fab.setEnabled(false);
        } else {
            userFeedBackEditText.setEnabled(true);
            progressBar.setVisibility(View.GONE);
            fab.setEnabled(true);
        }
    }

    private void sendComment(String feedback){
        setLoading(true);

        final ZendeskFeedbackConnector zendeskFeedbackConnector = new ZendeskFeedbackConnector(getActivity(), ZendeskConfig.INSTANCE.getContactConfiguration());

        if(zendeskFeedbackConnector.isValid()){
            zendeskFeedbackConnector.sendFeedback(feedback, null, createRequestZendeskCallback);
        }else{
            setLoading(false);
            Toast.makeText(getActivity(), getString(R.string.support_textview_sent_error), Toast.LENGTH_LONG).show();
        }

    }


    private final SafeZendeskCallback<CreateRequest> createRequestZendeskCallback = SafeZendeskCallback.from(new ZendeskCallback<CreateRequest>(){

        @Override
        public void onSuccess(CreateRequest o) {
            Toast.makeText(getActivity(), getString(R.string.support_textview_sent), Toast.LENGTH_LONG).show();
            FeedBackHelpFragment.this.getActivity().finish();
        }

        @Override
        public void onError(ErrorResponse errorResponse) {
            setLoading(false);
            Toast.makeText(getActivity(), getString(R.string.support_textview_sent_error), Toast.LENGTH_LONG).show();
        }
    });
}
