package lb.sivar.frags;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import lb.sivar.R;
import lb.sivar.conec.*;
import lb.sivar.db.dbHelper;

public class settingFragment extends Fragment {
    private dbHelper db;
    private String st;

    private EditText edit;
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup con,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.setting_fragment, con, false);
        edit = (EditText) v.findViewById(R.id.PhoneNumber);

        return v;
    }
}

