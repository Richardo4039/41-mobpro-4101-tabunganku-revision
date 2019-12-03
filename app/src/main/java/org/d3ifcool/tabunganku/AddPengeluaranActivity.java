package org.d3ifcool.tabunganku;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;

public class AddPengeluaranActivity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

    private EditText editTextPhone;
    private EditText editTextAmount;
    private EditText editTextDescription;
    private TextView textViewD3;
    private Button buttonAddD3;

    Calendar now = Calendar.getInstance();
    com.wdullaer.materialdatetimepicker.time.TimePickerDialog tpd;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pengeluaran);

        textViewD3 = findViewById(R.id.textViewD3);
        textViewD3.setVisibility(View.GONE);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                AddPengeluaranActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

//        tpd = com.wdullaer.materialdatetimepicker.time.TimePickerDialog.newInstance(
//                AddPengeluaranActivity.this,
//                now.get(Calendar.HOUR_OF_DAY),
//                now.get(Calendar.MINUTE),
//                now.get(Calendar.SECOND),
//                false
//        );

        buttonAddD3 = findViewById(R.id.button_d3);
        buttonAddD3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                insertPengeluaran();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertPengeluaran(){
//        editTextName = findViewById(R.id.edit_text_name);
        editTextPhone = findViewById(R.id.edit_text_phone);
        editTextAmount = findViewById(R.id.edit_text_amount);
        editTextDescription = findViewById(R.id.edit_text_description);

        FunctionPengeluaran functionPengeluaran = new FunctionPengeluaran(getBaseContext());
        Pengeluaran pengeluaran= new Pengeluaran();
//        pengeluaran.setName(editTextName.getText().toString().trim());
        pengeluaran.setTelphon(editTextPhone.getText().toString().trim());
        pengeluaran.setAmount(Integer.parseInt(editTextAmount.getText().toString()));
        pengeluaran.setDescription(editTextDescription.getText().toString().trim());
        pengeluaran.setDateDue(textViewD3.getText().toString().trim());

        if (TextUtils.isEmpty(editTextAmount.getText().toString().trim())||
                TextUtils.isEmpty(editTextPhone.getText().toString().trim())||
                TextUtils.isEmpty(editTextDescription.getText().toString().trim())){
            Toast.makeText(this, "Isi Semuanya!", Toast.LENGTH_SHORT).show();
        }else if (functionPengeluaran.create(pengeluaran)){
            Intent intentAdd = new Intent(AddPengeluaranActivity.this, MainActivity.class);
            Toast.makeText(AddPengeluaranActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            startActivity(intentAdd);
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Fail DB");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
        });
            builder.create().show();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR,year);
        now.set(Calendar.MONTH,monthOfYear);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currentDateString = DateFormat.getDateInstance().format(now.getTime());
        buttonAddD3.setText(currentDateString);
        textViewD3.setText(currentDateString);
//        dpd.show(getSupportFragmentManager(),"Datepickerdialog");
    }

//    @Override
//    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
////        now.set(Calendar.HOUR_OF_DAY,hourOfDay);
////        now.set(Calendar.MINUTE,minute);
////        now.set(Calendar.SECOND,second);
////        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
////        intent.putExtra("test","I am a String");
//    }
}

