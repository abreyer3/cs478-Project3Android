package cs478.allenbreyer.edu.project3_a2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import static cs478.allenbreyer.edu.project3_a2.ListViewFragment.menuPosition;


public class WebViewFragment extends Fragment
{
    private WebView mWebView;
    public String[] urlArray;
    private static final String TAG = "WebViewFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.webview_fragment, container, false);

        if (MainActivity.selectedActivity == 1)
        {
            urlArray = getResources().getStringArray(R.array.AttractionsUrl);
        }
        else
        {
            urlArray = getResources().getStringArray(R.array.RestaurantsUrl);
        }

        mWebView = view.findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(urlArray[menuPosition]);
        this.setRetainInstance(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        Log.i(TAG, "onViewCreated");

        ListViewFragment lvf = (ListViewFragment) getFragmentManager().findFragmentById(R.id.list_fragment);

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

        super.onViewCreated(view, savedInstanceState);
    }

    public void goTo(int position)
    {
        mWebView.loadUrl(urlArray[position]);
    }

}
