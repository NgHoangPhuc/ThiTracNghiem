package detai.android.activity.hocsinh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import detai.android.thitracnghiem.R;

public class LuatLeActivity extends AppCompatActivity {

    TextView tvContent;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hocsinh_luatle);

        addControls();
        addEvents();
        loadData();
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addControls() {

        tvContent = (TextView) findViewById(R.id.tvContent);
        btnBack = (Button) findViewById(R.id.btnBack);

    }

    private void loadData() {
        tvContent.setText(getResources().getString(R.string.app_rule));
    }
}
