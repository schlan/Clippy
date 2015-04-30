package at.droelf.clippy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.Arrays;
import java.util.List;

import at.droelf.clippy.broadcastreceiver.DeviceUnlock;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.utils.IntentHelper;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mReceiver;

    @InjectView(R.id.agent_list)
    RecyclerView recyclerView;

    @InjectView(R.id.agent_list_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        registerBroadcastListener();

        final int agentListOrientation = getResources().getInteger(R.integer.agent_list_orientation);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(getResources().getInteger(R.integer.agent_list_span), agentListOrientation));
        recyclerView.setAdapter(new AgentAdapter(getApplicationContext()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        registerBroadcastListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        unregisterBroadcastListener();
    }

    private void registerBroadcastListener(){
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        this.mReceiver = new DeviceUnlock();
        registerReceiver(mReceiver, filter);
    }

    private void unregisterBroadcastListener(){
        if(mReceiver != null){
            unregisterReceiver(mReceiver);
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
        public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
            final AgentAdapterViewHolder viewHolder1 = (AgentAdapterViewHolder) viewHolder;
            final AgentType agentType = agents.get(i);
            final Drawable drawable = context.getResources().getDrawable(agentType.getAgentMapping().getFirstFrameId());

            viewHolder1.itemView.setOnClickListener(new AgentClickListener(context, agentType));
            viewHolder1.getImageView().setBackgroundDrawable(drawable);
        }

        @Override
        public int getItemCount() {
            return agents.size();
        }
    }

    class AgentAdapterViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.agent_image)
        ImageView imageView;

        @InjectView(R.id.card_view)
        CardView cardView;

        public AgentAdapterViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
        }

        public ImageView getImageView(){
            return imageView;
        }

        public CardView getCardView() {
            return cardView;
        }
    }


}
