package com.recyclerp.all;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgwcapp.hgwcofficialapp.R;

import java.util.ArrayList;

/**
 * Created by ashiq on 05-Oct-16.
 */

public class RecyclerAdaptertwoItemsHV extends RecyclerView.Adapter<RecyclerAdaptertwoItemsHV.RecyclerHoldtwoitems> {


    private Context context;
    private ArrayList<HiddenVisibleDatatype> hvList;
    private LayoutInflater inflater;

    public RecyclerAdaptertwoItemsHV(ArrayList<HiddenVisibleDatatype> hvDataType, Context context) {
        this.hvList = hvDataType;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerHoldtwoitems onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.two_item_list_hidden_and_visible, parent, false);

        return new RecyclerHoldtwoitems(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHoldtwoitems holder, int position) {
        final HiddenVisibleDatatype hvitem = hvList.get(position);
        holder.tvHidden.setText(hvitem.getStringHidden());
        holder.tvVisible.setText(hvitem.getStringVisible());

        if(position == getItemCount() - 1) {
            holder.dividerLine.setVisibility(View.INVISIBLE);
        }

        /*holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return hvList.size();
    }

    class RecyclerHoldtwoitems extends RecyclerView.ViewHolder {


        TextView tvHidden;
        TextView tvVisible;
        View dividerLine;
        View container;

        public RecyclerHoldtwoitems(View itemView) {
            super(itemView);
            context = itemView.getContext();

            tvHidden = (TextView) itemView.findViewById(R.id.twoItemsHiddentv);
            tvVisible = (TextView) itemView.findViewById(R.id.twoItemsVisibletv);
            dividerLine=itemView.findViewById(R.id.dividerLine);

            container = itemView.findViewById(R.id.twoitemsLayoutHV);
        }
    }


}
