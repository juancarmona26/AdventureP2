package co.mobilemakers.chooseyourownadventure;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


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
        public void onClickButtons(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alley, container, false);

        prepareAdventureButtonsEvent();
        setAdventureButtonEvents();
        ContainerActivity.randomAlleyStates = new ArrayList<>();
        setDifferentRandomNumbers();

        return view;
    }

    private void setDifferentRandomNumbers() {
        for(int i = 0; i < 3; i++) {
            int randomNumber = ContainerActivity.randomNumberToShowView(100);
            while(ContainerActivity.randomAlleyStates.contains(randomNumber) && i != 0) {
                randomNumber = ContainerActivity.randomNumberToShowView(100);
            }
            ContainerActivity.randomAlleyStates.add(randomNumber);
        }
    }

    private void prepareAdventureButtonsEvent() {
        mButtonToWin = (Button) view.findViewById(R.id.button_go_right);
        mButtonToLoose = (Button) view.findViewById(R.id.button_go_left);
        mButtonToRoomView = (Button) view.findViewById(R.id.button_continue);
    }

    private void setAdventureButtonEvents(){

        mButtonToWin.setOnClickListener(clickListener);

        mButtonToLoose.setOnClickListener(clickListener);

        mButtonToRoomView.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean firstValidation;
            boolean secondValidation;

            switch (v.getId()){
                case R.id.button_go_right:
                    firstValidation = validateValueInPosition(0, 1);
                    secondValidation = validateValueInPosition(0, 2);

                    selectWhereToGo(firstValidation, secondValidation);

                    break;

                case R.id.button_continue:
                    firstValidation = validateValueInPosition(1, 0);
                    secondValidation = validateValueInPosition(1, 2);

                    selectWhereToGo(firstValidation, secondValidation);
                break;

                case R.id.button_go_left:
                    firstValidation = validateValueInPosition(2, 0);
                    secondValidation = validateValueInPosition(2, 1);

                    selectWhereToGo(firstValidation, secondValidation);
                break;
            }

        }
    };

    private void selectWhereToGo(boolean firstValidation, boolean secondValidation) {
        if(firstValidation && secondValidation){
            mCallback.onClickButtons(0);
        } else if((!firstValidation && secondValidation) || (firstValidation && !secondValidation)) {
            mCallback.onClickButtons(1);
        } else mCallback.onClickButtons(2);
    }

    private boolean validateValueInPosition(int currentPosition, int validationPosition ) {
        return ContainerActivity.randomAlleyStates.get(currentPosition) > ContainerActivity.randomAlleyStates.get(validationPosition);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnClickAdventureButtons) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnClickAdventureButtons");
        }
    }
}
