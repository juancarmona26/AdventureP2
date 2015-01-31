package co.mobilemakers.chooseyourownadventure;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    private static final String LOG_TAG = MainFragment.class.getName();
    View view;
    InitialEvents mCallBack;
    private Button mButtonStartAdventure;
    public MainFragment() {
        // Required empty public constructor
    }

    public interface InitialEvents {
        public void onButtonStartAdventureClicked();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Oncreate");
        view = inflater.inflate(R.layout.fragment_main, container, false);
        setViewsFromLayouts();
        setViewEvents();

        return view;
    }

    private void setViewsFromLayouts(){
        mButtonStartAdventure = (Button) view.findViewById(R.id.button_start_traveling);
    }

    private void setViewEvents(){
        mButtonStartAdventure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               mCallBack.onButtonStartAdventureClicked();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (InitialEvents) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement InitialEvents");
        }

    }
}
