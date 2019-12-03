package org.d3ifcool.tabunganku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivityPemasukkan extends AppCompatActivity {

    private TextView mAmount;
    private TextView mName;
    private TextView mPhone;
    private TextView mDescription;
    private TextView mDateOfLand;
    private TextView mDateDue;
    private RecyclerView recyclerView;
    private DBHelper mDbHelper = new DBHelper(this);
    private Cursor mCursor;
    private int mPayment;
    private TextView mTvPayment;
    private Pemasukkan pemasuk;

    @Override
    protected void onStart() {
        super.onStart();
        pemasuk = (Pemasukkan) getIntent().getSerializableExtra("pemasukkan");

        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();
        sqLiteDatabase.isOpen();

        mCursor = sqLiteDatabase.rawQuery("SELECT amount_pem FROM pemasukkan WHERE idPemasukkan = ?",new String[]{String.valueOf(pemasuk.getIdPemasukkan())});
        if (mCursor.moveToFirst()){
            mPayment = mCursor.getInt(0);
        }else {
            mPayment = -1;
        }
        mCursor.close();

        mTvPayment = findViewById(R.id.tv_payment);
        mTvPayment.setText(" ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemasukkan);

//        // TODO: 08/04/2019 back button
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        pemasuk = (Pemasukkan) intent.getSerializableExtra("pemasukkan");

//        mName = findViewById(R.id.tv_detail_name);
//        mName.setText(peminjaman.getName());
        mAmount = findViewById(R.id.tv_amount_pemasukkan);
        mAmount.setText(String.valueOf(pemasuk.getAmount_pem()));
        mDescription = findViewById(R.id.tv_desc_pemasukkan);
        mDescription.setText(pemasuk.getDesc_pem());
        mDateOfLand = findViewById(R.id.tv_date_pemasukkan);
        mDateOfLand.setText(pemasuk.getDateOfPemasukkan());
//        mDateDue = findViewById(R.id.tv_detail_dd);
//        mDateDue.setText(peminjaman.getDateDue());

        recyclerView = findViewById(R.id.rv_pemasukkan);
        FunctionPemasukkan functionPemasukkan=  new FunctionPemasukkan(this);
        ArrayList<Pemasukkan> listPemasukkan = new ArrayList<>();
        listPemasukkan.addAll(functionPemasukkan.findIdPemasukkan(pemasuk.getIdPemasukkan()));
        ListPemasukkanAdapter adapter = new ListPemasukkanAdapter(this,listPemasukkan);
        recyclerView.setAdapter(adapter);

//        FunctionPengeluaran functionPengeluaran =  new FunctionPengeluaran(this);
//        ArrayList<Pengeluaran> listPengeluaran = new ArrayList<>();
//        listPengeluaran.addAll(functionPengeluaran.findAll());
//        ListPengeluaranAdapter adapter = new ListPengeluaranAdapter(this,listPengeluaran);
//        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this,layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_debt_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit:
                editPeminjaman();
                break;
            case R.id.action_delete:
                deletePemasukkan();
                break;
//            case R.id.action_add_payment:
//                payAmount();
//                break;
            default:
                new IllegalArgumentException("Upslala");
        }

        return super.onOptionsItemSelected(item);
    }

    public void editPeminjaman(){
        pemasuk = (Pemasukkan) getIntent().getSerializableExtra("pemasukkan");
        pemasuk.setIdPemasukkan(pemasuk.getIdPemasukkan());
        pemasuk.setAmount_pem(pemasuk.getAmount_pem());
        pemasuk.setDesc_pem(pemasuk.getDesc_pem());
        pemasuk.setDateOfPemasukkan(pemasuk.getDateOfPemasukkan());

        Intent intentEdit = new Intent(DetailActivityPemasukkan.this, EditPengeluaranActivity.class);
        intentEdit.putExtra("pemasukkan", String.valueOf(pemasuk));
        startActivity(intentEdit);
    }

    public void deletePemasukkan(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi Hapus");
        builder.setMessage("Kamu yakin ingin menghapus?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FunctionPemasukkan functionPemasukkan= new FunctionPemasukkan(getBaseContext());
                if (functionPemasukkan.delete(pemasuk.getIdPemasukkan())){
                    Intent intentDelete = new Intent(DetailActivityPemasukkan.this,MainActivity.class);
                    startActivity(intentDelete);
                    Toast.makeText(DetailActivityPemasukkan.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder builderDeleteInformation = new AlertDialog.Builder(getBaseContext());
                    builderDeleteInformation.setCancelable(false);
                    builderDeleteInformation.setMessage("Fail");
                    builderDeleteInformation.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builderDeleteInformation.create().show();
                }
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public void payAmount(){
        pemasuk = (Pemasukkan) getIntent().getSerializableExtra("pemasukkan");
        pemasuk.setIdPemasukkan(pemasuk.getIdPemasukkan());
        pemasuk.setAmount_pem(pemasuk.getAmount_pem());
        pemasuk.setDesc_pem(pemasuk.getDesc_pem());
        pemasuk.setDateOfPemasukkan(pemasuk.getDateOfPemasukkan());

        Intent intentPay = new Intent(DetailActivityPemasukkan.this, EditPemasukkanActivity.class);
        intentPay.putExtra("pemasukkan", String.valueOf(pemasuk));
        startActivity(intentPay);
    }
}
