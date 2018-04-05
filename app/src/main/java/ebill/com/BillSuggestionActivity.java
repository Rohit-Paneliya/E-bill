package ebill.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BillSuggestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_suggestion);
    }

    public void onOkClicked(View view) {
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle(getString(R.string.app_name))
                .setText(getString(R.string.ebill_document_link))
                .startChooser();
    }

    public void onGenerateClicked(View view) {
        startActivity(new Intent(BillSuggestionActivity.this, GenerateBillActivity.class));
    }

    public void onCancelBillClicked(View view) {
        startActivity(new Intent(BillSuggestionActivity.this, CancelBillActivity.class));
    }

    public void onDownloadButtonClicked(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(getString(R.string.bill_download_link)));
        startActivity(i);
    }
}
