package ebill.com;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Locale;

public class SelectLanguageActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioGuj;
    private RadioButton radioEng;
    private RadioButton radioHin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        radioGroup = findViewById(R.id.radioGroup);
        radioGuj = findViewById(R.id.radioGuj);
        radioEng = findViewById(R.id.radioEng);
        radioHin = findViewById(R.id.radioHin);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioGuj: {
                        Resources res = getResources();
                        DisplayMetrics dm = res.getDisplayMetrics();
                        android.content.res.Configuration conf = res.getConfiguration();
                        conf.setLocale(new Locale("guj")); // API 17+ only.
                        res.updateConfiguration(conf, dm);
                    }

                    break;

                    case R.id.radioEng: {
                        Resources res = getResources();
                        DisplayMetrics dm = res.getDisplayMetrics();
                        android.content.res.Configuration conf = res.getConfiguration();
                        conf.setLocale(new Locale("en")); // API 17+ only.
                        res.updateConfiguration(conf, dm);
                    }
                    break;

                    case R.id.radioHin: {
                        Resources res = getResources();
                        DisplayMetrics dm = res.getDisplayMetrics();
                        android.content.res.Configuration conf = res.getConfiguration();
                        conf.setLocale(new Locale("hin")); // API 17+ only.
                        res.updateConfiguration(conf, dm);
                    }
                    break;
                }

                startActivity(new Intent(SelectLanguageActivity.this, BillSuggestionActivity.class));
            }
        });
    }
}
