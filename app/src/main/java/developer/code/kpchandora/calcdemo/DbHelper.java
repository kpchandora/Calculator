package developer.code.kpchandora.calcdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "calculator";

    private final String CREATE_TABLE =
            "CREATE TABLE " + DbUtils.TABLE_NAME + " ( " +
                    DbUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DbUtils.OPERATION_COLUMN + " TEXT, " +
                    DbUtils.MAIN_RESULTS_COLUMN + " TEXT, " +
                    DbUtils.DATE_COLUMN + " LONG);";

    private final String DELETE_TABLE = "DROP TABLE IF EXISTS " + DbUtils.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DELETE_TABLE);

        onCreate(sqLiteDatabase);

    }
}
