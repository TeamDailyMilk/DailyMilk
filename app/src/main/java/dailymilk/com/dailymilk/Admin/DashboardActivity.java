package dailymilk.com.dailymilk.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.LoginActivity;
import dailymilk.com.dailymilk.R;

public class DashboardActivity extends AppCompatActivity {


    public final static String EXTRA_USER = "";
    final Context ctx = this;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_exit_to_app_black_24dp);
        setSupportActionBar(myToolbar);

        TextView tv = new TextView(this);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_dashboard);

        username = getIntent().getStringExtra(EXTRA_USER);
        getSupportActionBar().setTitle(username.toString());

        Button b = (Button) findViewById(R.id.openord);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(ctx);
                backgroundWorker.execute("admin", username);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
