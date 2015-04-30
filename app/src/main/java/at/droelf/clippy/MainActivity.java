package at.droelf.clippy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import at.droelf.clippy.broadcastreceiver.DeviceUnlock;
import at.droelf.clippy.fragments.AgentPresentationFragment;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.raw.Agent;
import at.droelf.clippy.utils.IntentHelper;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.agent_list)
    RecyclerView viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        registerBroadcastListener();
        viewPager.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        viewPager.setAdapter(new AgentAdapter(getApplicationContext()));
    }

    private void registerBroadcastListener(){
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        final BroadcastReceiver mReceiver = new DeviceUnlock();
        registerReceiver(mReceiver, filter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        private final List<AgentType> agentTypes;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.agentTypes = Arrays.asList(AgentType.values());
        }

        @Override
        public Fragment getItem(int position) {
            return AgentPresentationFragment.newInstance(agentTypes.get(position));
        }

        @Override
        public int getCount() {
            return agentTypes.size();
        }
    }

    class AgentClickListener implements View.OnClickListener{

        private final AgentType agentType;
        private final Context context;

        public AgentClickListener(Context context, AgentType agentType){
            this.agentType = agentType;
            this.context = context;
        }


        @Override
        public void onClick(View v) {
            context.startService(IntentHelper.getCommandIntent(context, FloatingService.Command.Kill));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent commandIntent = IntentHelper.getCommandIntent(context, FloatingService.Command.Show);
                    commandIntent.putExtra(AgentType.KEY, agentType);
                    context.startService(commandIntent);
                }
            }, 500);
        }
    }

    class AgentAdapter extends RecyclerView.Adapter{

        private final List<AgentType> agents;
        private final Context context;

        public AgentAdapter(Context context){
            this.agents = Arrays.asList(AgentType.values());
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
            final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            final View inflate = inflater.inflate(R.layout.agent_list_item, viewGroup, false);
            return new AgentAdapterViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            final AgentAdapterViewHolder viewHolder1 = (AgentAdapterViewHolder) viewHolder;
            final AgentType agentType = agents.get(i);
            viewHolder1.itemView.setOnClickListener(new AgentClickListener(context, agentType));
            viewHolder1.getImageView().setImageDrawable(context.getResources().getDrawable(agentType.getAgentMapping().getFirstFrameId()));
            viewHolder1.getTextView().setText(agentType.name());
        }

        @Override
        public int getItemCount() {
            return agents.size();
        }
    }

    class AgentAdapterViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView textView;

        public AgentAdapterViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.agent_title);
            imageView = (ImageView) v.findViewById(R.id.agent_image);
        }

        public ImageView getImageView(){
            return imageView;
        }

        public TextView getTextView(){
            return textView;
        }

    }


}
