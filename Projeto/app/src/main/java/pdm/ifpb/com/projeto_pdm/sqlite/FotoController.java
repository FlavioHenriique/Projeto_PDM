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

    public void inserirFoto(String foto){

        db = create.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foto",foto);
        db.delete("foto",null,null);
        long i = db.insert("foto", null, values);
        System.out.println(i);
        db.close();
    }

    public String recuperaFoto(){

        Cursor cursor;
        String[] campos = {"foto"};
        db = create.getReadableDatabase();
        cursor = db.query("foto",campos,
                null,null,null,null,null);

        if(cursor!= null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor.getString(0);
    }
}
