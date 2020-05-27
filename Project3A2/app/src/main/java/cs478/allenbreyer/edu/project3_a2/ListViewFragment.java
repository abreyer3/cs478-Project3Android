package cs478.allenbreyer.edu.project3_a2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;



public class ListViewFragment extends ListFragment
{
    public static int menuPosition;
    private ListView mListView;
    private static final String TAG = "ListViewFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.listview_fragment, container, false);

        if (MainActivity.selectedActivity == 1)
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_activated_1, Attractions.attractionsArray);
            setListAdapter(adapter);
        }
        else
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_activated_1, Restaurants.restaurantsArray);
            setListAdapter(adapter);
        }

        mListView = view.findViewById(R.id.list_fragment);
        this.setRetainInstance(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        Log.i(TAG, "onViewCreated");
        ListViewFragment lvf = (ListViewFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        WebViewFragment wbf = (WebViewFragment) getFragmentManager().findFragmentById(R.id.webview);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        if(!MainActivity.selectedItem)
        {
            if (lvf != null)
            {
                Log.i(TAG, "nothing selected");
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                param.weight = 0;
                lvf.getView().setLayoutParams(param);
            }
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            if (MainActivity.selectedItem)
            {
                if (wbf != null && lvf != null) {
                    Log.i(TAG, "landscape, something selected");

                /*LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                param.weight = 3;
                lvf.getView().setLayoutParams(param);*/

                    transaction.show(wbf);
                    transaction.show(lvf);
                    transaction.commit();
                }
            }
        }
        else
        {
            if (MainActivity.selectedItem)
            {
                Log.i(TAG, "portrait, something selected");

                /*LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                param.weight = 0;
                lvf.getView().setLayoutParams(param);*/

                transaction.hide(lvf);
                transaction.commit();
            }
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        menuPosition = position;
        WebViewFragment wbf = (WebViewFragment) getFragmentManager().findFragmentById(R.id.webview);
        ListViewFragment lvf = (ListViewFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        //Fragment listFragment = getFragmentManager().findFragmentById(R.id.list_fragment);
        //LinearLayout ll = v.findViewById(R.id.restaurant_layout);
        //Fragment fragment = new WebViewFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        MainActivity.selectedItem = true;

        Log.i(TAG, "onListItemClick");
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            transaction.addToBackStack(null);
            transaction.show(wbf);
            //transaction.hide(fragment);
            transaction.commit();

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            param.weight = 3;
            if (lvf != null)
            {
                //ll.setLayoutParams(param);
                lvf.getView().setLayoutParams(param);
            }
        }
        else
        {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            param.weight = 3;
            if (lvf != null)
            {
                lvf.getView().setLayoutParams(param);
            }

            transaction.addToBackStack(null);
            transaction.show(wbf);
            transaction.hide(lvf);
            transaction.commit();
        }

        wbf.goTo(position);

        getListView().setSelector(android.R.color.holo_blue_dark);
        //v.setSelected(true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        Log.i(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}
