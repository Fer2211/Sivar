package lb.sivar.frags;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lb.sivar.R;
import lb.sivar.adap.adapterChat;
import lb.sivar.contacts;
import lb.sivar.db.dbHelper;
import lb.sivar.message;


/**
 * Created by HP on 20/6/2017.
 */

public class counselingFragment extends Fragment implements adapterChat.OnItemClickListener {
    private static String TAG = "-------->fragMen";
    
    private View v;
    private RecyclerView list;
    private LinearLayoutManager lManager;
    private adapterChat adapt;
    private dbHelper base;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup con,
                            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.chat_fragment, con, false);

        FloatingActionButton fab = v.findViewById(R.id.chat_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),contacts.class);
                startActivity(i);
            }
        });


        list = (RecyclerView) v.findViewById(R.id.chats);
        list.setHasFixedSize(true);

        lManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(lManager);

        adapt = new adapterChat(getActivity(), this);
        list.setAdapter(adapt);

        base = new dbHelper(getActivity());
        try{
            Cursor c = base.getAllChats();
            while (c.moveToNext()) { adapt.swapCursor(c); }
        }catch(Exception e){}
        return v;
    }

    @Override
    public void onClick(adapterChat.ViewHolder holder, String chat) {
        Log.d(TAG, "onClick: " + chat);
        Intent i = new Intent(getActivity(),message.class);
        i.putExtra("chat",chat);
        startActivity(i);
    }
}