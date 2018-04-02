package ebill.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class CancelBillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_bill);
    }

    public void onSubmitClicked(View view) {
        Toast.makeText(this, "Cancel bill", Toast.LENGTH_SHORT).show();
    }
}
