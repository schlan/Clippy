package at.droelf.clippy;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import at.droelf.clippy.broadcastreceiver.DeviceUnlock;
import at.droelf.clippy.model.AgentType;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        final BroadcastReceiver mReceiver = new DeviceUnlock();
        registerReceiver(mReceiver, filter);

        final Spinner spinner = (Spinner) findViewById(R.id.agent_spinner);
        spinner.setAdapter(new SpinnerAdapter());


        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window = new Intent(MainActivity.this, FloatingService.class);
                window.putExtra(FloatingService.Command.KEY, FloatingService.Command.Show);
                window.putExtra(AgentType.KEY, (AgentType)spinner.getSelectedItem());
                startService(window);
            }
        });


        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window = new Intent(MainActivity.this, FloatingService.class);
                window.putExtra(FloatingService.Command.KEY, FloatingService.Command.Start);
                startService(window);
            }
        });


        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window = new Intent(MainActivity.this, FloatingService.class);
                window.putExtra(FloatingService.Command.KEY, FloatingService.Command.Stop);
                startService(window);
            }
        });


        findViewById(R.id.kill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window = new Intent(MainActivity.this, FloatingService.class);
                window.putExtra(FloatingService.Command.KEY, FloatingService.Command.Kill);
                startService(window);
            }
        });


    }

    class SpinnerAdapter extends BaseAdapter{

        private final List<AgentType> agentTypeList;

        public SpinnerAdapter(){
            this.agentTypeList = Arrays.asList(AgentType.values());
        }

        @Override
        public int getCount() {
            return agentTypeList.size();
        }

        @Override
        public AgentType getItem(int position) {
            return agentTypeList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if(v == null){
                v = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            final AgentType item = getItem(position);
            final TextView textView = ((TextView)v.findViewById(android.R.id.text1));
            textView.setText(item.toString());
            textView.setTextColor(Color.BLACK);
            textView.setCompoundDrawablesWithIntrinsicBounds(item.getAgentMapping().getFirstFrameId(), 0, 0, 0);

            return v;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
