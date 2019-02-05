package telis.christou.contactlist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText searchField;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.searchField);

        db = openOrCreateDatabase("ContactList", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` ( " +
                    "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    "`email` TEXT NOT NULL UNIQUE, `fullname` TEXT NOT NULL, " +
                    "`address` TEXT, `telephone` TEXT NOT NULL );"
                  );
        db.execSQL("INSERT OR IGNORE INTO `users` (id,email,fullname,address,telephone) VALUES (1,'dev@example.com','Lloyd A. Dyer','3250 Jones Street Dallas, TX 75201','817-519-3404');");
        db.execSQL("INSERT OR IGNORE INTO `users` (id,email,fullname,address,telephone) VALUES (2,'dev@example.gr','Judy J. Flores','1285 Grove Street','631-755-9584'); ");
        db.execSQL("INSERT OR IGNORE INTO `users` (id,email,fullname,address,telephone) VALUES (3,'dev@example.com.gr','Florine D. Briscoe','3402 Snowbird Lane','402-679-0081');");
        db.execSQL("INSERT OR IGNORE INTO `users` (id,email,fullname,address,telephone) VALUES (4,'dev@example.edu','Seymour E. Redford','304 Waterview Lane','505-452-2009');");
    }

    public void search(View v) {
        String searchValue = searchField.getText().toString();

        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra("searchValue", searchValue);
        intent.putExtra("dbPath", db.getPath());
        startActivity(intent);
    }
}
