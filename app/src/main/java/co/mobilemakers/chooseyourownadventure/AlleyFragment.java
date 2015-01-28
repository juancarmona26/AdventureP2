package co.mobilemakers.chooseyourownadventure;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlleyFragment extends Fragment {
    public OnClickAdventureButtons mCallback;

    private Button mButtonToWin;
    private Button mButtonToRoomView;
    private Button mButtonToLoose;

    View view;


    public AlleyFragment() {

    }

    public interface OnClickAdventureButtons {
        public void onClickButtonToWin();
        public void onClickButtonToRandomView();
        public void onClickButtonToLoose();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alley, container, false);

        prepareAdventureButtonsEvent();
        setAdventureButtonEvents();

        return view;
    }

    private void prepareAdventureButtonsEvent() {
        mButtonToWin = (Button) view.findViewById(R.id.button_go_right);
        mButtonToLoose = (Button) view.findViewById(R.id.button_go_left);
        mButtonToRoomView = (Button) view.findViewById(R.id.button_continue);
    }

    private void setAdventureButtonEvents(){

        mButtonToWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onClickButtonToWin();
            }
        });

        mButtonToLoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onClickButtonToLoose();
            }
        });

        mButtonToRoomView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCallback.onClickButtonToRandomView();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnClickAdventureButtons) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
