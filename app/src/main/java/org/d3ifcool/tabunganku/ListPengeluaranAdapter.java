package org.d3ifcool.tabunganku;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListPengeluaranAdapter extends RecyclerView.Adapter<ListPengeluaranAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Pengeluaran> listPengeluaran;

    public ListPengeluaranAdapter(Context mContext, ArrayList<Pengeluaran> listPengeluaran) {
        this.mContext = mContext;
        this.listPengeluaran = listPengeluaran;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_view_pengeluaran_layout, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Pengeluaran pengeluaran= listPengeluaran.get(i);
        viewHolder.text_view__nomor.setText(pengeluaran.getTelphon());
        viewHolder.text_view__hutang.setText(String.valueOf(pengeluaran.getAmount()));
        viewHolder.text_view__keterangan.setText(pengeluaran.getDescription());
        viewHolder.text_view__batas_tanggal.setText(pengeluaran.getDateOfPengeluaran());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetailPengeluaran = new Intent(mContext,DetailActivity.class);
                intentDetailPengeluaran.putExtra("pengeluaran",pengeluaran);
                mContext.startActivity(intentDetailPengeluaran);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPengeluaran.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_view_nama_peminjam, text_view__nomor, text_view__keterangan, text_view__hutang, text_view__tanggal_pinjam, text_view__batas_tanggal, idPeminjaman;
        ImageView iv_pengeluaran;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_pengeluaran = itemView.findViewById(R.id.imageView8);
            text_view__nomor = itemView.findViewById(R.id.text_view_nomor);
            text_view__keterangan = itemView.findViewById(R.id.text_view_keterangan);
            text_view__hutang = itemView.findViewById(R.id.text_v_nominal_pengeluaran);
            text_view__tanggal_pinjam = itemView.findViewById(R.id.text_view_tanggal_pinjam);
            text_view__batas_tanggal = itemView.findViewById(R.id.text_v_tgl_pengeluaran);
        }
    }
}
