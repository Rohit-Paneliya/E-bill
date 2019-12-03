package ebill.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CancelBillActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_SEND_SMS = 101;
    private TextView txtBillNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_bill);

        txtBillNo = findViewById(R.id.txtBillNo);
    }

    public void onCancelBillClicked(View view) {
        //finalCancelEWayBill();
        Toast.makeText(this, "Under development", Toast.LENGTH_SHORT).show();
    }

    /*private void finalCancelEWayBill() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

                Toast.makeText(this, "Go to Setting -> App -> Allow SMS permission.", Toast.LENGTH_SHORT).show();
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            sendSms();
        }
    }

    private void sendSms() {

        //Bill number
        if (txtBillNo.getText().toString().trim().length() < 12) {
            Toast.makeText(this, "Please enter 12 digit number.", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(R.string.cancel_bill_popup);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(getString(R.string.ebill_mob_no), null, smsTextFormat(), null, null);
                        Toast.makeText(getApplicationContext(), "SMS sent successfully.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(CancelBillActivity.this, SubmitSuccessActivity.class));
                    }
                });

        builder1.setNegativeButton(
                R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private String smsTextFormat() {
        return "EWBC " + txtBillNo.getText().toString();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSms();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, Please allow SMS permission.", Toast.LENGTH_LONG).show();
                }
            }
        }

    }*/
}
