package org.d3ifcool.tabunganku;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListPemasukkanAdapter extends RecyclerView.Adapter<ListPemasukkanAdapter.ViewHolder>{
    Context mContext;
    ArrayList<Pemasukkan> listPemasukkan;

    public ListPemasukkanAdapter(Context mContext, ArrayList<Pemasukkan> listPemasukkan) {
        this.mContext = mContext;
        this.listPemasukkan = listPemasukkan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_view_pemasukkan_layout, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Pemasukkan pemasukkan= listPemasukkan.get(i);
        viewHolder.tv_nominal_pemasukkan.setText(Integer.toString(pemasukkan.getAmount_pem()));
        viewHolder.tv_desc_pem.setText(pemasukkan.getDesc_pem());
        viewHolder.text_view_date_pemasukkan.setText(String.valueOf(pemasukkan.getDateOfPemasukkan()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetailPemasukkan = new Intent(mContext,DetailActivityPemasukkan.class);
//                intentDetailPemasukkan.putExtra("pemasukkan", pemasukkan);
                intentDetailPemasukkan.putExtra("pemasukkan", pemasukkan);
                mContext.startActivity(intentDetailPemasukkan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPemasukkan.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_nominal_pemasukkan, tv_desc_pem, text_view_date_pemasukkan;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_nominal_pemasukkan = itemView.findViewById(R.id.text_view_nominal_pemasukkan);
            tv_desc_pem = itemView.findViewById(R.id.text_view_desc_pem);
            text_view_date_pemasukkan = itemView.findViewById(R.id.text_view_tgl_pemasukkan);
        }
    }
}
