package co.mobilemakers.chooseyourownadventure;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Random;


public class ContainerActivity extends FragmentActivity {

    public static final String LOG_TAG = ContainerActivity.class.getName();

    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        chooseFragmentRamdomly();

//        if (findViewById(R.id.layout_container) != null) {
//
//            // However, if we're being restored from a previous state,
//            // then we don't need to do anything and should return or else
//            // we could end up with overlapping fragments.
//            if (savedInstanceState != null) {
//                return;
//            }
//
//            AlleyFragment alleyFragment = new AlleyFragment();
//            getFragmentManager().beginTransaction().add(R.id.layout_container, alleyFragment).commit();
//
//        }
    }

    private void chooseFragmentRamdomly(){
        int randomNumber = random.nextInt(2) + 1;
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
}
