package lb.sivar.frags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import lb.sivar.R;

public class loginFragment extends Fragment {
    private TextView lName;
    private TextView lPhone;
    private Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.login_fragment, container, false);
        lName = (TextView) v.findViewById(R.id.login_name);
        lPhone = (TextView) v.findViewById(R.id.login_phone);
        login = (Button) v.findViewById(R.id.login_sign_in);
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });
    }

    private void makeRequest(){
    }
}
