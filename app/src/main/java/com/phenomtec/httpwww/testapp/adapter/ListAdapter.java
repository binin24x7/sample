package com.phenomtec.httpwww.testapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phenomtec.httpwww.testapp.R;
import com.phenomtec.httpwww.testapp.model.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binin Regi on 19/05/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewHolder> {

    List<Item> items=new ArrayList<>();

    public void loadItems(List<Item> items){
        Log.d("ListAdapter","loadItems"+items.size());
        this.items=items;
        notifyDataSetChanged();
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        public viewHolder(View itemView) {
            super(itemView);
            tvName=((TextView)itemView.findViewById(R.id.tv_name));

        }

        public  void setItem(Item item){

            Log.d("setItem",""+item.sector_name);
            tvName.setText(item.sector_name);
        }
    }

}
