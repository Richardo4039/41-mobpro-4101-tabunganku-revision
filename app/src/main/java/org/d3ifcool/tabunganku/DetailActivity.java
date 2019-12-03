package org.d3ifcool.tabunganku;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

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
    private Pengeluaran peminjaman;

    @Override
    protected void onStart() {
        super.onStart();
        peminjaman = (Pengeluaran) getIntent().getSerializableExtra("pengeluaran");

        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();
        sqLiteDatabase.isOpen();

        mCursor = sqLiteDatabase.rawQuery("SELECT amount FROM pengeluaran WHERE idPengeluaran = ?",new String[]{String.valueOf(peminjaman.getidPengeluaran())});
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
        setContentView(R.layout.activity_detail);

        // TODO: 08/04/2019 back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        peminjaman = (Pengeluaran) intent.getSerializableExtra("pengeluaran");

        mName = findViewById(R.id.tv_detail_name);
        mName.setText(peminjaman.getTelphon());
        mAmount = findViewById(R.id.tv_detail_amount);
        mAmount.setText(String.valueOf(peminjaman.getAmount()));
        mDescription = findViewById(R.id.tv_detail_description);
        mDescription.setText(peminjaman.getDescription());
        mDateOfLand = findViewById(R.id.tv_detail_dl);
        mDateOfLand.setText(peminjaman.getDateOfPengeluaran());
        mDateDue = findViewById(R.id.tv_detail_dd);
        mDateDue.setText(peminjaman.getDateOfPengeluaran());

        recyclerView = findViewById(R.id.rv_pemasukkan);
        FunctionPengeluaran functionPengeluaran =  new FunctionPengeluaran(this);
        ArrayList<Pengeluaran> listPengeluaran = new ArrayList<>();
        listPengeluaran.addAll(functionPengeluaran.findIdPengeluaran(peminjaman.getidPengeluaran()));
        ListPengeluaranAdapter adapter = new ListPengeluaranAdapter(this,listPengeluaran);
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
                deletePeminjaman();
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
        peminjaman = (Pengeluaran) getIntent().getSerializableExtra("pengeluaran");
        peminjaman.setidPengeluaran(peminjaman.getidPengeluaran());
        peminjaman.setAmount(peminjaman.getAmount());
        peminjaman.setName(peminjaman.getName());
        peminjaman.setTelphon(peminjaman.getTelphon());
        peminjaman.setDescription(peminjaman.getDescription());
        peminjaman.setDateOfPengeluaran(peminjaman.getDateOfPengeluaran());
        peminjaman.setDateDue(peminjaman.getDateDue());

        Intent intentEdit = new Intent(DetailActivity.this, EditPengeluaranActivity.class);
        intentEdit.putExtra("pengeluaran",peminjaman);
        startActivity(intentEdit);
    }

    public void deletePeminjaman(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi Hapus");
        builder.setMessage("Kamu yakin ingin menghapus?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FunctionPengeluaran functionPeminjaman = new FunctionPengeluaran(getBaseContext());
                if (functionPeminjaman.delete(peminjaman.getidPengeluaran())){
                    Intent intentDelete = new Intent(DetailActivity.this,MainActivity.class);
                    startActivity(intentDelete);
                    Toast.makeText(DetailActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
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
        peminjaman = (Pengeluaran) getIntent().getSerializableExtra("pengeluaran");
        peminjaman.setidPengeluaran(peminjaman.getidPengeluaran());
        peminjaman.setAmount(peminjaman.getAmount());
        peminjaman.setName(peminjaman.getName());
        peminjaman.setTelphon(peminjaman.getTelphon());
        peminjaman.setDescription(peminjaman.getDescription());
        peminjaman.setDateOfPengeluaran(peminjaman.getDateOfPengeluaran());
        peminjaman.setDateDue(peminjaman.getDateDue());

        Intent intentPay = new Intent(DetailActivity.this, EditPengeluaranActivity.class);
        intentPay.putExtra("pengeluaran",peminjaman);
        startActivity(intentPay);
    }

//    public void call(){
//        peminjaman = (Pengeluaran) getIntent().getSerializableExtra("pengeluaran");
//        String phoneNumber = peminjaman.getTelphon();
//        Intent dialPhoneNumber = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
//        startActivity(dialPhoneNumber);
//    }
}
