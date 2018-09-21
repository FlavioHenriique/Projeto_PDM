package pdm.ifpb.com.projeto_pdm.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FotoController {

    private SQLiteDatabase db;
    private CreateBD create;

    public FotoController(Context context){
        create = new CreateBD(context);
    }

    public void inserirFoto(String foto, String email){

        db = create.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foto",foto);
        values.put("email",email);
        db.delete("foto","email = ?", new String[]{email});
        long i = db.insert("foto", null, values);
        System.out.println(i);
        db.close();
    }

    public String recuperaFoto(String email){

        Cursor cursor;
        String sql  = "SELECT foto from foto where email = ?";
        db = create.getReadableDatabase();
        cursor = db.rawQuery(sql,new String[]{email});
        if(cursor!= null && cursor.getCount()>0){
            cursor.moveToFirst();
            db.close();
            return cursor.getString(0);
        }else{
            return "";
        }

    }
}
