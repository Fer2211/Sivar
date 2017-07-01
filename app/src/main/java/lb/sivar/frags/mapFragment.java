package lb.sivar.frags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lb.sivar.R;

/**
 * Created by HP on 20/6/2017.
 */

public class mapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup con,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.map_fragment, con, false);
    }
}
