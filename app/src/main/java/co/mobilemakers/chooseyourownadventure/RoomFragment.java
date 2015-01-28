package co.mobilemakers.chooseyourownadventure;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RoomFragment extends Fragment {
    public OnClickButtons mOnClickButtons;
    Button mButtonRoomOne;
    Button mButtonRoomTwo;
    Button mButtonRandom;
    View view;

    public interface OnClickButtons{
        public void onButtonRoomOneClicked();
        public void onButtonRoomTwoClicked();
        public void onButtonRandomClicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_room, container, false);

        prepareButtons();
        setEventButtons();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnClickButtons = (OnClickButtons) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    private void prepareButtons(){
        mButtonRoomOne =(Button) view.findViewById(R.id.button_door_1);
        mButtonRoomTwo =(Button) view.findViewById(R.id.button_door_2);
        mButtonRandom =(Button) view.findViewById(R.id.button_random);
    }

    private void setEventButtons() {
        mButtonRoomOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mOnClickButtons.onButtonRoomOneClicked();
            }
        });

        mButtonRoomTwo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mOnClickButtons.onButtonRoomTwoClicked();
            }
        });

        mButtonRandom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mOnClickButtons.onButtonRandomClicked();
            }
        });
    }


}
