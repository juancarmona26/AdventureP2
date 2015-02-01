package co.mobilemakers.chooseyourownadventure;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class RoomFragment extends Fragment {
    public OnClickButtons mOnClickButtons;
    Button mButtonRoomOne;
    Button mButtonRoomTwo;
    Button mButtonRandom;
    View view;

    public interface OnClickButtons{
        public void onButtonClicked(int whereToFall);
        public void onButtonRandomClicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_room, container, false);

        prepareButtons();
        setEventButtons();
        ContainerActivity.randomRoomStates = new ArrayList<>();
        setDifferentRandomNumbers();

        return view;
    }

    private void setDifferentRandomNumbers() {
        for(int i = 0; i < 2; i++) {
            int randomNumber = ContainerActivity.randomNumberToShowView(100);
            while(ContainerActivity.randomRoomStates.contains(randomNumber) && i != 0) {
                randomNumber = ContainerActivity.randomNumberToShowView(100);
            }
            ContainerActivity.randomRoomStates.add(randomNumber);
        }
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

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean firstValidation;

            switch (v.getId()){
                case R.id.button_door_1:
                    firstValidation = validateValueInPosition(0, 1);
                    selectWhereToGo(firstValidation);

                    break;

                case R.id.button_door_2:
                    firstValidation = validateValueInPosition(1, 0);
                    selectWhereToGo(firstValidation);
                    break;
            }

        }
    };

    private void selectWhereToGo(boolean firstValidation) {
        if(firstValidation){
            mOnClickButtons.onButtonClicked(0);
        } else mOnClickButtons.onButtonClicked(1);
    }

    private boolean validateValueInPosition(int currentPosition, int validationPosition ) {
        return ContainerActivity.randomRoomStates.get(currentPosition) > ContainerActivity.randomRoomStates.get(validationPosition);
    }

    private void setEventButtons() {
        mButtonRoomOne.setOnClickListener(clickListener);

        mButtonRoomTwo.setOnClickListener(clickListener);

        mButtonRandom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mOnClickButtons.onButtonRandomClicked();
            }
        });
    }


}
