package org.d3ifcool.tabunganku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    // TODO : Tambahin Contract sesuai dengan Aplikasi Pets

    private static final String DATABASE_NAME = "tabunganku";
    private static final int DATABASE_VERSION = 10;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.w("Version","Currect version is "+ sqLiteDatabase.getVersion());
        String pengeluaran = "CREATE TABLE pengeluaran (" +
                "idPengeluaran INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "telphon VARCHAR,"+
                "amount INTEGER,"+
                "description TEXT,"+
                "dateOfPengeluaran DATETIME)";

        String pembayaran = "CREATE TABLE pembayaran (idPembayaran INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "idPeminjaman INTEGER,"+
                "datePay DATETIME,"+
                "pay INTEGER,"+
                "description TEXT)";

        String pemasukkan = "CREATE TABLE pemasukkan (idPemasukkan INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "amount_pem INTEGER,"+
                "desc_pem TEXT,"+
                "dateOfPemasukkan DATETIME)";

        sqLiteDatabase.execSQL(pemasukkan);
        sqLiteDatabase.execSQL(pengeluaran);
        sqLiteDatabase.execSQL(pembayaran);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w("Update DB", "DB IS UPDATE TO "+sqLiteDatabase.getVersion());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS peminjaman");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pembayaran");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pemasukkan");
        onCreate(sqLiteDatabase);
    }
}