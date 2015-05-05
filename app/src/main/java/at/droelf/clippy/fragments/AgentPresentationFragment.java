package at.droelf.clippy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import at.droelf.clippy.R;
import at.droelf.clippy.model.AgentType;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class AgentPresentationFragment extends Fragment{

    @InjectView(R.id.agent_image)
    ImageView agentImage;


    public static AgentPresentationFragment newInstance(AgentType agentType){
        final AgentPresentationFragment agentPresentationFragment = new AgentPresentationFragment();
        final Bundle bundle = new Bundle();
        bundle.putSerializable(AgentType.KEY, agentType);
        agentPresentationFragment.setArguments(bundle);
        return agentPresentationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_agent_presentation, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AgentType agentType = (AgentType) getArguments().getSerializable(AgentType.KEY);
        agentImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), agentType.getAgentMapping().getFirstFrameId()));
    }


}
