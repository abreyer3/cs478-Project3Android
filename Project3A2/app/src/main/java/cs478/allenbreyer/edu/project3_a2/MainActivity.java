package cs478.allenbreyer.edu.project3_a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    public static boolean selectedItem;
    public static int selectedActivity;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedItem = false;
        selectedActivity = 1;

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("cs478.allenbreyer.edu.project3_a1_attractions");
        filter.addAction("cs478.allenbreyer.edu.project3_a1_restaurants");
        registerReceiver(receiver, filter, "edu.uic.cs478.sp2020.project3", null);
    }

    @Override
    protected void onStop()
    {
        //unregisterReceiver(receiver);
        super.onStop();
    }
}
