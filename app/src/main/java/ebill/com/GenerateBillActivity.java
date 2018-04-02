package ebill.com;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GenerateBillActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_SEND_SMS = 101;
    String destinationAddress = "9731979899";
    Calendar billDate = Calendar.getInstance();
    String[] transCode;
    private Spinner spinnerTransType;
    private EditText txtRecGstIn;
    private EditText txtPincode;
    private EditText txtBillNo;
    private TextView txtBillDate;
    private EditText txtBillValue;
    private EditText txtHsnCode;
    private EditText txtDistance;
    private EditText txtVehicalNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        transCode = getResources().getStringArray(R.array.transportationCode);

        spinnerTransType = findViewById(R.id.spinnerTransType);
        txtRecGstIn = findViewById(R.id.txtRecGstIn);
        txtPincode = findViewById(R.id.txtPincode);
        txtBillNo = findViewById(R.id.txtBillNo);
        txtBillDate = findViewById(R.id.txtBillDate);
        txtBillValue = findViewById(R.id.txtBillValue);
        txtHsnCode = findViewById(R.id.txtHsnCode);
        txtDistance = findViewById(R.id.txtDistance);
        txtVehicalNo = findViewById(R.id.txtVehicalNo);

        txtBillDate.setText(dateFormat.format(new Date()));

        txtBillDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(GenerateBillActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        billDate.set(year, month, dayOfMonth);
                        txtBillDate.setText(dateFormat.format(billDate.getTime()));
                    }
                }, billDate.get(Calendar.YEAR), billDate.get(Calendar.MONTH), billDate.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
    }

    public void onSubmitClicked(View view) {

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

        // Recipient's GSTIN
        if (txtRecGstIn.getText().toString().trim().length() < 3) {
            Toast.makeText(this, "Please enter RecGSTIN with 3 or 15 digit.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Bill pincode
        if (txtPincode.getText().toString().trim().length() < 6) {
            Toast.makeText(this, "Please enter 6 digit valid pincode.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Bill number
        if (txtBillNo.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please enter bill number.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Bill date
        if (txtBillDate.getText().toString().equals("Select date")) {
            Toast.makeText(this, "Please select date.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Bill Value
        if (txtBillValue.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please enter bill amount.", Toast.LENGTH_SHORT).show();
            return;
        }

        //HSN no
        int hsnLength = txtHsnCode.getText().toString().trim().length();
        if (hsnLength < 2) {
            Toast.makeText(this, "Please enter HSN code between 2 to 10 digits.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Distance
        if (txtDistance.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please enter approx distance.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Vehicle number
        if (txtVehicalNo.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please enter vehicle number.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Log.d("-------", "sendSms: " + setMsgFormat());
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(getString(R.string.ebill_mob_no), null, setMsgFormat(), null, null);
        Toast.makeText(getApplicationContext(), "SMS sent successfully.", Toast.LENGTH_LONG).show();
    }

    @NonNull
    private String setMsgFormat() {
        String billValue = txtBillValue.getText().toString();

        return "EWBG " + transCode[spinnerTransType.getSelectedItemPosition()] + " " + txtRecGstIn.getText().toString() + " " + txtPincode.getText().toString() + " " +
                txtBillNo.getText().toString() + " " + txtBillDate.getText().toString() + " " + billValue + " " +
                txtHsnCode.getText().toString() + " " + txtDistance.getText().toString() + " " + txtVehicalNo.getText().toString();
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

    }
}
