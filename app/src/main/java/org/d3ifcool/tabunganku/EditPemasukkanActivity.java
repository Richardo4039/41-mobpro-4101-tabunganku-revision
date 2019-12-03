package org.d3ifcool.tabunganku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;

public class EditPemasukkanActivity extends AppCompatActivity {

    private EditText amount;
    private EditText desc;
    private TextView mDd;
    private Button mBtnDateDue;
    private Pemasukkan pemasuk;

    Calendar now = Calendar.getInstance();
    //    com.wdullaer.materialdatetimepicker.time.TimePickerDialog tpd;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pengeluaran);

        pemasuk = (Pemasukkan) getIntent().getSerializableExtra("pemasukkan");

//        mName = findViewById(R.id.edit_text_name);
//        mName.setText(peminjaman.getName());

        amount= findViewById(R.id.edit_pemasukkan);
        amount.setText(pemasuk.getAmount_pem());

        desc = findViewById(R.id.edit_desc_pemasukkan);
        desc.setText(String.valueOf(pemasuk.getDesc_pem()));

//        mDescription = findViewById(R.id.edit_text_description);
//        mDescription.setText(peminjaman.getDescription());
//
        mDd = findViewById(R.id.hiden);
        mDd.setText(pemasuk.getDateOfPemasukkan());

        dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                (DatePickerDialog.OnDateSetListener) this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

//        tpd = com.wdullaer.materialdatetimepicker.time.TimePickerDialog.newInstance(
//                this,
//                now.get(Calendar.HOUR_OF_DAY),
//                now.get(Calendar.MINUTE),
//                now.get(Calendar.SECOND),
//                false
//        );

        mBtnDateDue = findViewById(R.id.btn_d3);
        mBtnDateDue.setText(pemasuk.getDateOfPemasukkan());
        mBtnDateDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                updatePemasukkan();
                break;
            default:
                new IllegalArgumentException("Upslala");
        }
        return super.onOptionsItemSelected(item);
    }


    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR,year);
        now.set(Calendar.MONTH,monthOfYear);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(now.getTime());
        mBtnDateDue.setText(currentDateString);
        mDd.setText(currentDateString);

    }

//    @Override
//    public void onTimeSet(com.wdullaer.materialdatetimepicker.time.TimePickerDialog view, int hourOfDay, int minute, int second) {
//        now.set(Calendar.HOUR_OF_DAY,hourOfDay);
//        now.set(Calendar.MINUTE,minute);
//        now.set(Calendar.SECOND,second);
//        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//        intent.putExtra("test","I am a String");
//
////        NotifyMe.Builder notifyMe = new NotifyMe.Builder(getApplicationContext());
////        notifyMe.title("Ups sudah jatuh tempo pembayaran");
////        notifyMe.content("Coba cek hutang ke "+mName.getText());
////        notifyMe.color(255,0,0,255);
////        notifyMe.led_color(255,255,255,255);
////        notifyMe.time(now);
////        notifyMe.delay(0);
////        notifyMe.large_icon(R.mipmap.ic_launcher_round);
////        notifyMe.addAction(intent,"Snooze",false);
////        notifyMe.addAction(new Intent(),"Dismiss",true,false);
////        notifyMe.addAction(intent,"done");
////        notifyMe.build();
//    }

    public void updatePemasukkan(){
        FunctionPemasukkan functionPemasukkan= new FunctionPemasukkan(getBaseContext());
//        peminjaman.setName(mName.getText().toString().trim());
        pemasuk.setAmount_pem(Integer.parseInt(amount.getText().toString().trim()));
        pemasuk.setDesc_pem(desc.getText().toString().trim());
        pemasuk.setDateOfPemasukkan(mDd.getText().toString().trim());

        if (
                TextUtils.isEmpty(amount.getText().toString().trim())||
                        TextUtils.isEmpty(desc.getText().toString().trim())){
            Toast.makeText(this, "please compleated!", Toast.LENGTH_SHORT).show();
        }else if(functionPemasukkan.Update(pemasuk)){
            Intent intentUpdate = new Intent(EditPemasukkanActivity.this, DetailActivityPemasukkan.class);
            intentUpdate.putExtra("pemasukkan", String.valueOf(pemasuk));
            Toast.makeText(EditPemasukkanActivity.this, "Data berhasil diedit", Toast.LENGTH_SHORT).show();
            startActivity(intentUpdate);
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("fail");
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
}
