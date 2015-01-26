package co.mobilemakers.chooseyourownadventure;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Random;


public class ContainerActivity extends FragmentActivity implements AlleyFragment.OnClickAdventureButtons {

    public static final String LOG_TAG = ContainerActivity.class.getName();
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        chooseFragmentRandomly();

    }

    private void chooseFragmentRandomly(){
        int randomNumber = 1;//random.nextInt(2) + 1;
        Log.d(LOG_TAG, "Random number = "+ randomNumber);

        switch (randomNumber){

            case 1: AlleyFragment alleyFragment = new AlleyFragment();
                    getFragmentManager().beginTransaction().add(R.id.layout_container, alleyFragment).commit();
            break;

            case 2: RoomFragment roomFragment = new RoomFragment();
                    getFragmentManager().beginTransaction().add(R.id.layout_container, roomFragment).commit();
            break;

            default:

        }

    }

    @Override
    public void onClickButtonToWin() {

        Log.d(LOG_TAG, "You Won");
        WinnerFragment winnerFragment = new WinnerFragment();
//        args.putInt(ArticleFragment.ARG_POSITION, position);
//        newFragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.layout_container, winnerFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

    @Override
    public void onClickButtonToRoomView() {
        Log.d(LOG_TAG, "RoomView");
    }

    @Override
    public void onClickButtonToLoose() {
        LooserFragment newFragment = new LooserFragment();
//        args.putInt(ArticleFragment.ARG_POSITION, position);
//        newFragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
    // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.layout_container, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }
}
