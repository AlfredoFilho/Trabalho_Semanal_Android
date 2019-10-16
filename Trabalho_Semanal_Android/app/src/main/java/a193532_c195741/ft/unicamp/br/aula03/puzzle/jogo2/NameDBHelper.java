package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NameDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "JOGO2";
    private static final int DB_VERSION = 1;

    NameDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE tabela " +
                "(_id INTEGER PRIMARY KEY);");*/

        db.execSQL("CREATE TABLE MaisErradoImagem (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome Text, qtd INTEGER);");
        db.execSQL("CREATE TABLE MaisErradoBotao (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome Text, qtd INTEGER);");
        db.execSQL("CREATE TABLE PorcentAmostral (_id INTEGER PRIMARY KEY AUTOINCREMENT, porcent Text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}
