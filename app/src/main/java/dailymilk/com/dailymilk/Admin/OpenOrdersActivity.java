package dailymilk.com.dailymilk.Admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import dailymilk.com.dailymilk.BackgroundRemover;
import dailymilk.com.dailymilk.R;

public class OpenOrdersActivity extends AppCompatActivity {


    public final static String EXTRA_RESULT = "";
    public final static String EXTRA_USERNAME = "";
    ArrayAdapter<String> adapter;
    ListView chl;
    private ArrayList<String> orderArray;
    private ArrayList<String> idArray;
    private ArrayList<String> remidArray = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        populate();
    }

    public void populate() {
        String[] result = getIntent().getStringExtra(EXTRA_RESULT).split(";");
        orderArray = new ArrayList<>(Arrays.asList(result));
        idArray = new ArrayList<>();

        for (int i = 0; orderArray.size() > i; i++) {
            String str = orderArray.get(i).toString();
            String numberonly = str.replaceAll("[^0-9]+", "").trim();
            idArray.add(i, numberonly);
            String item = str.replaceAll("[^A-Za-z\\s]", "");
            orderArray.set(i, item);
        }

        chl = (ListView) findViewById(R.id.checkableList);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<>(this, R.layout.rowlayout, R.id.txt_lan, orderArray);
        chl.setAdapter(adapter);
    }

    public void removeSelectedItems(MenuItem menuItem) {

        SparseBooleanArray checkedItems = chl.getCheckedItemPositions();
        int itemCount = chl.getCount();

        int j = 0;
        for (int i = itemCount - 1; i >= 0; i--) {
            if (checkedItems.get(i)) {
                sendRemovedIds(idArray.get(i).toString());
                idArray.remove(i);
                adapter.remove(orderArray.get(i));
            }
        }

        checkedItems.clear();
        remidArray.clear();
        adapter.notifyDataSetChanged();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.openorders, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                removeSelectedItems(item);
                break;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void sendRemovedIds(String id) {
        BackgroundRemover backgroundRemover = new BackgroundRemover(this);
        backgroundRemover.execute(id);
    }
}