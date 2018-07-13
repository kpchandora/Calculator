package developer.code.kpchandora.calcdemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        makeList();
    }

    private void makeList(){
        ResultAdapter adapter = new ResultAdapter(fetchData(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.clear_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DbHelper helper = new DbHelper(ResultActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE  FROM " + DbUtils.TABLE_NAME + " WHERE " + DbUtils._ID + " != -1");
        makeList();
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<ResultsModel> fetchData() {
        DbHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<ResultsModel> resultList = new ArrayList<>();

        Cursor cursor = db.query(DbUtils.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DbUtils._ID + " DESC");
        Log.i(TAG, "fetchData: " + cursor);

        int mainResultIndex = cursor.getColumnIndexOrThrow(DbUtils.MAIN_RESULTS_COLUMN);
        int operationResult = cursor.getColumnIndexOrThrow(DbUtils.OPERATION_COLUMN);

        while (cursor.moveToNext()) {
            ResultsModel model = new ResultsModel();
            model.setMainResult(cursor.getString(mainResultIndex));
            model.setOperations(cursor.getString(operationResult));
            resultList.add(model);
        }

        cursor.close();
        return resultList;
    }
}
