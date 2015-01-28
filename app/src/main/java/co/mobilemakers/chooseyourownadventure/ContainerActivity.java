package co.mobilemakers.chooseyourownadventure;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class ContainerActivity extends FragmentActivity implements AlleyFragment.OnClickAdventureButtons, RoomFragment.OnClickButtons {

    public static final String LOG_TAG = ContainerActivity.class.getName();

    private Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        chooseFragmentRandomly();

    }

    private void chooseFragmentRandomly(){
        int randomNumber = randomNumberToShowView();
        Log.d(LOG_TAG, "Random number = "+ randomNumber);

        switch (randomNumber){

            case 1:
                    AlleyFragment alleyFragment = new AlleyFragment();
                    getFragmentManager().beginTransaction().add(R.id.layout_container, alleyFragment).commit();
            break;

            case 2: RoomFragment roomFragment = new RoomFragment();
                    getFragmentManager().beginTransaction().add(R.id.layout_container, roomFragment).commit();
            break;

            default:

        }

    }

    private int randomNumberToShowView () {
        return random.nextInt(2) + 1;
    }

    @Override
    public void onClickButtonToWin() {
        WinnerFragment winnerFragment = new WinnerFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, winnerFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClickButtonToRandomView() {
        int randomNumber = randomNumberToShowView();
        FragmentTransaction transaction;
        switch (randomNumber){

            case 1: Toast.makeText(getApplicationContext(), "That button brought you to here", Toast.LENGTH_LONG).show();
                    AlleyFragment alleyFragment = new AlleyFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_container, alleyFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                break;

            case 2: Toast.makeText(getApplicationContext(), "That button brought you to here", Toast.LENGTH_LONG).show();
                    RoomFragment roomFragment = new RoomFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_container, roomFragment);
                    transaction.addToBackStack(null);

                transaction.commit();
                break;

            default:

        }
    }

    @Override
    public void onClickButtonToLoose() {
        LooserFragment newFragment = new LooserFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void showResult(String resultToShow) {
        ResultFragment newFragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString("resultToShow", resultToShow);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onButtonRoomOneClicked(){
        showResult("You've reached the gold!");
    }

    @Override
    public void onButtonRoomTwoClicked() {
        showResult("You've fallen in to the pit of despair");
    }

    @Override
    public void onButtonRandomClicked() {
        onClickButtonToRandomView();
    }
}
