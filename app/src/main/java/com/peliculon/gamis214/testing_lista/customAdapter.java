package com.peliculon.gamis214.testing_lista;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peliculon.gamis214.testing_lista.model.FeedResponse;
import com.peliculon.gamis214.testing_lista.model.itemList;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by JAAH on 21/02/17.
 */

public class customAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<itemList> listaItems;
    private View.OnClickListener listener;

    public customAdapter(Context context, List<itemList> listaItems,View.OnClickListener listener){
        this.context = context;
        this.listaItems = listaItems;
        this.listener = listener;
    }

    public static class itemViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView txtName,txtState;
        private LinearLayout container;

        public itemViewHolder(View view){
            super(view);

            imageView = (ImageView) view.findViewById(R.id.image);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtState = (TextView) view.findViewById(R.id.txtstate);
            container = (LinearLayout) view.findViewById(R.id.container);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        itemViewHolder itemHolder = (itemViewHolder) holder;

        String url = listaItems.get(position).getImg();
        String name = listaItems.get(position).getName();
        String state = listaItems.get(position).getState();

        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(itemHolder.imageView);


        itemHolder.txtName.setText(name);
        itemHolder.txtState.setText(state);

        itemHolder.container.setOnClickListener(listener);
        itemHolder.container.setTag(listaItems.get(position));

    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }
}
