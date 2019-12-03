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

import java.text.DateFormat;
import java.util.Calendar;

public class AddPemasukkanActivity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

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
        setContentView(R.layout.activity_add_pemasukkan);

        textViewD3 = findViewById(R.id.textViewD3);
        textViewD3.setVisibility(View.GONE);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                AddPemasukkanActivity.this,
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
                insertPemasukkan();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertPemasukkan(){
//        editTextName = findViewById(R.id.edit_text_name);
        editTextAmount = findViewById(R.id.edit_pemasukkan);
        editTextDescription = findViewById(R.id.edit_desc_pemasukkan);

        FunctionPemasukkan functionPemasukkan= new FunctionPemasukkan(getBaseContext());
        Pemasukkan pemasukkan= new Pemasukkan();
//        pengeluaran.setName(editTextName.getText().toString().trim());
//        pengeluaran.setTelphon(editTextPhone.getText().toString().trim());
        pemasukkan.setAmount_pem(Integer.parseInt(editTextAmount.getText().toString()));
        pemasukkan.setDesc_pem(editTextDescription.getText().toString().trim());
        pemasukkan.setDateOfPemasukkan(textViewD3.getText().toString().trim());

        if (TextUtils.isEmpty(editTextAmount.getText().toString().trim())||
                TextUtils.isEmpty(editTextDescription.getText().toString().trim())){
            Toast.makeText(this, "Isi Semuanya!", Toast.LENGTH_SHORT).show();
        }else if (functionPemasukkan.create(pemasukkan)){
            Intent intentAdd = new Intent(AddPemasukkanActivity.this, MainActivity.class);
            Toast.makeText(AddPemasukkanActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
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
