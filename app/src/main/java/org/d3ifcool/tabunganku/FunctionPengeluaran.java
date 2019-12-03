package org.d3ifcool.tabunganku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FunctionPengeluaran {
    private DBHelper dbHelper;

    public FunctionPengeluaran(Context context){
        dbHelper = new DBHelper(context);
    }

    public List<Pengeluaran> findAll(){
        try {
            List<Pengeluaran> pengeluarans = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM pengeluaran",null);
            if (cursor.moveToFirst()){
                do {
                    Pengeluaran peminjaman = new Pengeluaran();
                    peminjaman.setidPengeluaran(cursor.getInt(0));
//                    peminjaman.setName(cursor.getString(1));
                    peminjaman.setTelphon(cursor.getString(1));
                    peminjaman.setAmount(cursor.getInt(2));
                    peminjaman.setDescription(cursor.getString(3));
                    peminjaman.setDateOfPengeluaran(cursor.getString(4));
//                    peminjaman.setDateDue(cursor.getString(6));
                    pengeluarans.add(peminjaman);
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return  pengeluarans;
        }catch (Exception ex){
            return null;
        }
    }

    public List<Pengeluaran> findIdPengeluaran(int idPengeluaran){
        try {
            List<Pengeluaran> pengeluarans = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM pengeluaran WHERE idPengeluaran = ?",new String[]{String.valueOf(idPengeluaran)});
            if (cursor.moveToFirst()){
                do {
                    Pengeluaran peminjaman = new Pengeluaran();
                    peminjaman.setidPengeluaran(cursor.getInt(0));
//                    peminjaman.setName(cursor.getString(1));
                    peminjaman.setTelphon(cursor.getString(1));
                    peminjaman.setAmount(cursor.getInt(2));
                    peminjaman.setDescription(cursor.getString(3));
                    peminjaman.setDateOfPengeluaran(cursor.getString(4));
                    pengeluarans.add(peminjaman);
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return  pengeluarans;
        }catch (Exception ex){
            return null;
        }
    }

    public boolean create(Pengeluaran peminjaman){
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues= new ContentValues();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
            String strDate = sdf.format(new Date());

//            contentValues.put("name", peminjaman.getName());
            contentValues.put("telphon", peminjaman.getTelphon());
            contentValues.put("amount", peminjaman.getAmount());
            contentValues.put("description", peminjaman.getDescription());
//            contentValues.put("dateOfLoan", strDate);
            contentValues.put("dateOfPengeluaran", peminjaman.getDateDue());
            long rows = sqLiteDatabase.insert("pengeluaran",null, contentValues);
            sqLiteDatabase.close();
            return rows > 0;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean delete(int idPengeluaran){
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            int rows = sqLiteDatabase.delete("pengeluaran","idPengeluaran = ?",new String[]{String.valueOf(idPengeluaran)});
            sqLiteDatabase.close();
            return rows>0;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean Update(Pengeluaran peminjaman){
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
//            contentValues.put("name", peminjaman.getName());
            contentValues.put("telphon", peminjaman.getTelphon());
            contentValues.put("amount", peminjaman.getAmount());
            contentValues.put("description", peminjaman.getDescription());
            contentValues.put("dateDue", peminjaman.getDateDue());
            int rows = sqLiteDatabase.update("pengeluaran",contentValues,"idPengeluaran = ?", new String[]{String.valueOf(peminjaman.getidPengeluaran())});
            sqLiteDatabase.close();
            return rows > 0;
        }catch (Exception ex){
            return false;
        }
    }
}
