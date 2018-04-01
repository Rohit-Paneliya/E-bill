package ebill.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BillSuggestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_suggestion);
    }

    public void onOkClicked(View view) {
        startActivity(new Intent(BillSuggestionActivity.this, DashboardActivity.class));
    }
}
