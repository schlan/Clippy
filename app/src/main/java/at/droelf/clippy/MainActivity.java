package at.droelf.clippy;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import at.droelf.clippy.broadcastreceiver.DeviceUnlock;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new DeviceUnlock();
        registerReceiver(mReceiver, filter);

        setContentView(R.layout.activity_main);

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window = new Intent(MainActivity.this, FloatingService.class);
                window.putExtra(FloatingService.Command.KEY, FloatingService.Command.Show);
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
