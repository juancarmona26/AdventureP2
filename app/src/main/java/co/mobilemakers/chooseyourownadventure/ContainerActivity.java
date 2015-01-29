package co.mobilemakers.chooseyourownadventure;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class ContainerActivity extends Activity implements  AlleyFragment.OnClickAdventureButtons,
                                                                    RoomFragment.OnClickButtons,
                                                                    MainFragment.InitialEvents{

    public static final String LOG_TAG = ContainerActivity.class.getName();

    private Random random = new Random();
    MainFragment mainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        showMainFragment();
    }

    private void showMainFragment() {
        mainFragment  = new MainFragment();
        getFragmentManager().beginTransaction().add(R.id.layout_container, mainFragment).commit();
    }

    private void chooseFragmentRandomly(){
        int randomNumber = randomNumberToShowView();
        FragmentTransaction transaction;
        Log.d(LOG_TAG, "Random number = "+ randomNumber);

        switch (randomNumber){

            case 1: Log.d(LOG_TAG," Entra");
                    AlleyFragment alleyFragment = new AlleyFragment();

                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_container, alleyFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.addToBackStack(null);
                    transaction.commit();
            break;

            case 2: Log.d(LOG_TAG," Entra2");
                    RoomFragment roomFragment = new RoomFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_container, roomFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.addToBackStack(null);

                    transaction.commit();
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
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
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
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.addToBackStack(null);
                    transaction.commit();

                break;

            case 2: Toast.makeText(getApplicationContext(), "That button brought you to here", Toast.LENGTH_LONG).show();
                    RoomFragment roomFragment = new RoomFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_container, roomFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.addToBackStack(null);

                    transaction.commit();

                break;

            default: Log.d(LOG_TAG," Default");

        }
    }

    @Override
    public void onClickButtonToLoose() {
        LooserFragment newFragment = new LooserFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, newFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
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
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
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

    @Override
    public void onButtonStartAdventureClicked() {
        chooseFragmentRandomly();
    }


}
