package cs478.allenbreyer.edu.project3_a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;


public class Restaurants extends AppCompatActivity {

    public static String[] restaurantsArray;
    private static final String TAG = "Restaurants";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        restaurantsArray = getResources().getStringArray(R.array.Restaurants);

        setContentView(R.layout.activity_fragments);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.attractions:
                MainActivity.selectedItem = false;
                MainActivity.selectedActivity = 1;
                Intent i1 = new Intent(this, Attractions.class);
                startActivity(i1);
                return true;
            case R.id.restaurants:
                MainActivity.selectedItem = false;
                MainActivity.selectedActivity = 2;
                Intent i2 = new Intent(this, Restaurants.class);
                startActivity(i2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        Log.i(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed()
    {
        Log.i(TAG, "onBackPressed");
        WebViewFragment wbf = (WebViewFragment) getSupportFragmentManager().findFragmentById(R.id.webview);
        ListViewFragment lvf = (ListViewFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);

        MainActivity.selectedItem = false;

        if (wbf != null && lvf != null)
        {
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                param.weight = 0;
                if (lvf != null)
                {
                    lvf.getView().setLayoutParams(param);
                }
            }
            else
            {
                FragmentManager fm = this.getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.show(lvf);
                transaction.hide(wbf);
                transaction.commit();
            }
        }

        super.onBackPressed();
    }
}
