package cs478.allenbreyer.edu.project3_a1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_PROJECT3 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, "edu.uic.cs478.sp2020.project3");

        if(PackageManager.PERMISSION_GRANTED == permissionCheck)
        {

        }
        else
        {
            requestProjectPermission();
        }
    }

    private void requestProjectPermission()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "edu.uic.cs478.sp2020.project3"))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for project 3 to work normally.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"edu.uic.cs478.sp2020.project3"},
                                    MY_PERMISSIONS_PROJECT3);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{"edu.uic.cs478.sp2020.project3"},
                    MY_PERMISSIONS_PROJECT3);
        }
    }

    public void sendAttractions (View view)
    {
        Intent i = new Intent();
        i.setAction("cs478.allenbreyer.edu.project3_a1_attractions");
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
        Toast.makeText(getApplicationContext(),"Sending broadcast intent for Attractions",Toast.LENGTH_SHORT).show();
    }

    public void sendRestaurants (View view)
    {
        Intent i = new Intent();
        i.setAction("cs478.allenbreyer.edu.project3_a1_restaurants");
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
        Toast.makeText(getApplicationContext(),"Sending broadcast intent for Restaurants",Toast.LENGTH_SHORT).show();
    }
}
