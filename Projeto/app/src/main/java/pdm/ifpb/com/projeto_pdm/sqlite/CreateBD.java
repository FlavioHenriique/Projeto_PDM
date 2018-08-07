package pdm.ifpb.com.projeto_pdm.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateBD extends SQLiteOpenHelper {

    private static final String NOME = "foto.db";
    private static final int VERSAO = 1;

    public CreateBD(Context context) {
        super(context, NOME, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE foto (foto varchar);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
