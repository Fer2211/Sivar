package lb.sivar.adap;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lb.sivar.R;
import lb.sivar.commons;
import lb.sivar.db.dbHelper;
import lb.sivar.obj.objMen;

import java.util.ArrayList;

public class adapterMen extends BaseAdapter {

    private static commons commons;
    private static dbHelper db;

    private static LayoutInflater inflater = null;
    ArrayList<objMen> chatMessageList;

    public adapterMen(Activity activity, ArrayList<objMen> list) {
        db = new dbHelper(activity);
        chatMessageList = list;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return chatMessageList.size(); }

    @Override
    public Object getItem(int position) { return position; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        objMen message = chatMessageList.get(position);
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.message_design, null);

        commons = new commons();
        TextView msg = vi.findViewById(R.id.message_text);
        TextView dt = vi.findViewById(R.id.message_date);
        msg.setText(message.getMessage());
        dt.setText(commons.getTime(message.getDate()));
        ConstraintLayout layout = vi.findViewById(R.id.bubble_layout);
        RelativeLayout parent_layout =  vi.findViewById(R.id.message_bubble);

        if (message.getWho()) {
            layout.setBackgroundResource(R.drawable.bubble2);
            parent_layout.setGravity(Gravity.END);
        }else {
            layout.setBackgroundResource(R.drawable.bubble1);
            parent_layout.setGravity(Gravity.START);
        }
        msg.setTextColor(Color.BLACK);
        return vi;
    }
    public void init(Cursor c){
        for (int i=0;c.moveToPosition(i);i++){
            c.moveToPosition(i);
            objMen men = new objMen(c.getString(0),c.getString(1),c.getInt(2),c.getString(3),c.getLong(4));
            chatMessageList.add(men);
        }
    }
    public void add(objMen object) { chatMessageList.add(object); db.saveMen(object); }
}
