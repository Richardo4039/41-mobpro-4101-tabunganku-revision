package org.d3ifcool.tabunganku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FunctionPemasukkan {
    private DBHelper dbHelper;

    public FunctionPemasukkan(Context context){
        dbHelper = new DBHelper(context);
    }

    public List<Pemasukkan> findAll(){
        try {
            List<Pemasukkan> pemasukkans = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM pemasukkan",null);
            if (cursor.moveToFirst()){
                do {
                    Pemasukkan pemasuk = new Pemasukkan();
                    pemasuk.setIdPemasukkan(cursor.getInt(0));
//                    peminjaman.setName(cursor.getString(1));
                    pemasuk.setAmount_pem(cursor.getInt(1));
                    pemasuk.setDesc_pem(cursor.getString(2));
                    pemasuk.setDateOfPemasukkan(cursor.getString(3));
//                    peminjaman.setDateDue(cursor.getString(6));
                    pemasukkans.add(pemasuk);
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return  pemasukkans;
        }catch (Exception ex){
            return null;
        }
    }

    public List<Pemasukkan> findIdPemasukkan(int idPemasukkan){
        try {
            List<Pemasukkan> pemasukkans= new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM pemasukkan WHERE idPemasukkan = ?",new String[]{String.valueOf(idPemasukkan)});
            if (cursor.moveToFirst()){
                do {
                    Pemasukkan pemasuk= new Pemasukkan();
                    pemasuk.setIdPemasukkan(cursor.getInt(0));
//                    peminjaman.setName(cursor.getString(1));
                    pemasuk.setAmount_pem(cursor.getInt(1));
                    pemasuk.setDesc_pem(cursor.getString(2));
                    pemasuk.setDateOfPemasukkan(cursor.getString(3));
//                    peminjaman.setDateDue(cursor.getString(6));
                    pemasukkans.add(pemasuk);
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return  pemasukkans;
        }catch (Exception ex){
            return null;
        }
    }

    public boolean create(Pemasukkan pemasuk){
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues= new ContentValues();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
            String strDate = sdf.format(new Date());

//            contentValues.put("name", peminjaman.getName());
            contentValues.put("amount_pem", pemasuk.getAmount_pem());
            contentValues.put("desc_pem",pemasuk.getDesc_pem());
            contentValues.put("dateOfPemasukkan", pemasuk.getDateOfPemasukkan());
//            contentValues.put("dateDue", peminjaman.getDateDue());
            long rows = sqLiteDatabase.insert("pemasukkan",null, contentValues);
            sqLiteDatabase.close();
            return rows > 0;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean delete(int idPemasukkan){
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            int rows = sqLiteDatabase.delete("pemasukkan","idPemasukkan = ?",new String[]{String.valueOf(idPemasukkan)});
            sqLiteDatabase.close();
            return rows>0;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean Update(Pemasukkan pemasuk){
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
//            contentValues.put("name", peminjaman.getName());
            contentValues.put("amount_pem", pemasuk.getAmount_pem());
            contentValues.put("desc_pem",pemasuk.getDesc_pem());
            contentValues.put("dateOfPemasukkan", pemasuk.getDateOfPemasukkan());
//            contentValues.put("dateDue", peminjaman.getDateDue());
            int rows = sqLiteDatabase.update("pemasukkan",contentValues,"idPemasukkan = ?", new String[]{String.valueOf(pemasuk.getIdPemasukkan())});
            sqLiteDatabase.close();
            return rows > 0;
        }catch (Exception ex){
            return false;
        }
    }
}
