package lb.sivar.adap;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lb.sivar.R;

/**
 * Created by HP on 25/6/2017.
 */

public class adapterChat extends RecyclerView.Adapter<adapterChat.ViewHolder> {
    private static String TAG = "-------->menAdap";
    private final Context contexto;
    private Cursor items;
    private OnItemClickListener escucha;

    public adapterChat(Context contexto, OnItemClickListener escucha) {
        this.contexto = contexto;
        this.escucha = escucha;
    }

    public interface OnItemClickListener {
        void onClick(ViewHolder holder, String chat);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Referencias UI
        public TextView vContact;
        public TextView vID;
        public ImageView viewFoto;

        public ViewHolder(View v) {
            super(v);
            vContact = (TextView) v.findViewById(R.id.chat_contact);
            vID = (TextView) v.findViewById(R.id.chat_id);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(this, chat(getAdapterPosition()));
        }
    }

    private String chat(int posicion) {
        if (items != null) {
            if (items.moveToPosition(posicion)) {
                return items.getString(chat.ID);
            }
        }
        return null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_design, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        items.moveToPosition(position);

        String s;

        // Asignación UI
        s = items.getString(chat.ID);
        holder.vID.setText(s);

        s = items.getString(chat.CONTACT);
        holder.vContact.setText(s);

        //s = items.getString(ConsultaAlquileres.URL);
        //Glide.with(contexto).load(s).centerCrop().into(holder.viewFoto);
    }

    @Override
    public int getItemCount() {
        if (items != null){ return items.getCount(); }
        return 0;
    }

    public void swapCursor(Cursor c) {
        if (c != null) {
            items = c;
            notifyDataSetChanged();
        }
    }

    public Cursor getCursor() {
        return items;
    }

    interface chat {
        int ID = 0;
        int CONTACT = 1;
        int ANONYM = 2;
    }
}