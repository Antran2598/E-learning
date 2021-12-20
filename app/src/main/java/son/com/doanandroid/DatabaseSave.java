package son.com.doanandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseSave extends SQLiteOpenHelper {
    public static final String SaveScore_TABLE = "Save_Score";
    public static final String COLUMN_id = "id";
    public static final String COLUMN_ten = "ten";
    public static final String COLUMN_Mssv = "Mssv";
    public static final String COLUMN_MudDo = "MucDo";

    public DatabaseSave(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"tracnghiem.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlStatement = "CREATE TABLE IF NOT EXISTS " + SaveScore_TABLE + " (" + COLUMN_id + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_ten + " TEXT , "
                +COLUMN_Mssv + " TEXT , " + COLUMN_MudDo + " TEXT )";
        sqLiteDatabase.execSQL(sqlStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }



}
