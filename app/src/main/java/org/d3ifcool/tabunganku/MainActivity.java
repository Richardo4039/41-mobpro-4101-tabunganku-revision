package org.d3ifcool.tabunganku;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    RecyclerView ListPengeluaran;

//
//    public MainActivity(Context context){
//        dbHelper = new DBHelper(context);
//    }
    private RecyclerView recyclerView,recyclerView1;
    private Pengeluaran pengeluaran;
//    private View loadingIndicator;
    private TextView tvNominal;
    private ImageView backdrop;
    private DBHelper mDbHelper = new DBHelper(this);
    private Cursor mCursor;
    private int mPayment;

    private TextView totalPengeluaran, totalPemasukkan;

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onStart() {
        super.onStart();

//        pengeluaran = (Pengeluaran) getIntent().getSerializableExtra("pengeluaran");
//
//        SQLiteDatabase sqLiteDatabase = mDbHelper.getWritableDatabase();
//        sqLiteDatabase.isOpen();
//
//        mCursor = sqLiteDatabase.rawQuery("SELECT amount FROM pengeluaran",new String[]{String.valueOf(pengeluaran.getidPengeluaran())});
//        if (mCursor.moveToFirst()){
//            mPayment = mCursor.getInt(0);
//        }else {
//            mPayment = -1;
//        }
//        mCursor.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Intent intent = getIntent();
//        pengeluaran = (Pengeluaran) intent.getSerializableExtra("pengeluaran");
//
//        totalPengeluaran = findViewById(R.id.total_pengeluaran);
//        totalPengeluaran.setText(String.valueOf(pengeluaran.getAmount()));



//        Pengeluaran pengeluaran = new Pengeluaran();
//        tvNominal = findViewById(R.id.tv_nominal_pengeluaran);
//        tvNominal.setText(pengeluaran.getAmount());


        com.getbase.floatingactionbutton.FloatingActionButton fbPemasukkan = findViewById(R.id.fabPemasukkan);
        fbPemasukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddPemasukkanActivity.class);
                startActivity(intent);
            }
        });

        com.getbase.floatingactionbutton.FloatingActionButton fbPengeluaran = findViewById(R.id.fabPengeluaran);
        fbPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddPengeluaranActivity.class);
                startActivity(intent);
            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,AddPengeluaranActivity.class);
//                startActivity(intent);
//            }
//        });

        recyclerView = findViewById(R.id.list_view_item);

        FunctionPengeluaran functionPengeluaran =  new FunctionPengeluaran(this);
        ArrayList<Pengeluaran> listPengeluaran = new ArrayList<>();
        listPengeluaran.addAll(functionPengeluaran.findAll());
        ListPengeluaranAdapter adapter = new ListPengeluaranAdapter(this,listPengeluaran);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this,layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);

        //===============================================================================================================//

        recyclerView1 = findViewById(R.id.list_view_item1);

        FunctionPemasukkan functionPemasukkan = new FunctionPemasukkan(this);
        ArrayList<Pemasukkan> listPemasukkan = new ArrayList<>();
        listPemasukkan.addAll(functionPemasukkan.findAll());
        ListPemasukkanAdapter adapter1 = new ListPemasukkanAdapter(this, listPemasukkan);
        recyclerView1.setAdapter(adapter1);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager1);

        DividerItemDecoration divider1 = new DividerItemDecoration(this,layoutManager.getOrientation());
        recyclerView1.addItemDecoration(divider1);



        if (recyclerView == null && recyclerView1 == null){
            backdrop = findViewById(R.id.backdrop);
            backdrop.setVisibility(View.VISIBLE);
        }

    }
}
