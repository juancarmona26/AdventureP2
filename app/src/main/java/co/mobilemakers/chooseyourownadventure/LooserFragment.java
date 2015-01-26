package co.mobilemakers.chooseyourownadventure;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by USER on 25/01/2015.
 */
public class LooserFragment extends Fragment {

    View view;

    public LooserFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_looser, container, false);

        return view;

    }
}
