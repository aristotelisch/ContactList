package telis.christou.contactlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class ContactDetailsActivity extends AppCompatActivity {
    String searchValue;
    TextView emailText;
    SQLiteDatabase db;
    String dbPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Intent intent = getIntent();
        searchValue = intent.getStringExtra("searchValue");
        dbPath = intent.getStringExtra("dbPath");
        db = SQLiteDatabase.openDatabase(dbPath, null, MODE_PRIVATE);

        emailText = findViewById(R.id.emailText);
        emailText.setText(searchValue);

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = '" + searchValue +"';", null);

        if (cursor.getCount() > 0 ) {
            cursor.moveToNext();
            TextView id = findViewById(R.id.userID);
            TextView email = findViewById(R.id.userEmail);
            TextView fullName = findViewById(R.id.userFullName);
            TextView address = findViewById(R.id.userAddress);
            TextView telephone = findViewById(R.id.userTelephone);

            id.setText(cursor.getString(0));
            email.setText(cursor.getString(1));
            fullName.setText(cursor.getString(2));
            address.setText(cursor.getString(3));
            telephone.setText(cursor.getString(4));
        }
        cursor.close();
    }

}
