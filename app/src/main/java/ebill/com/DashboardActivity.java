package ebill.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void onGenerateClicked(View view) {
        startActivity(new Intent(DashboardActivity.this, GenerateBillActivity.class));
    }

    public void onCancelBillClicked(View view) {
        startActivity(new Intent(DashboardActivity.this, CancelBillActivity.class));
    }
}
