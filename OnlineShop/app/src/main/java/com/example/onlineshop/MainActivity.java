package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String mainProducts[]={"Shoes", "Clothes", "Bags", "Jewelry"};
    String mainDescription[]={"Click this product for more details!", "Click this product for more details!", "Click this product for more details!", "Click this product for more details!"};
    String productsDescription[]={"Shoes description.", "Clothes description.", "Bags description.", "Jewelry description."};;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        textView = (TextView)findViewById(R.id.textview3);

        MyAdapter adapter = new MyAdapter(this, mainProducts, mainDescription);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, productsDescription[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("mylifecycle", "onStart was called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("mylifecycle", "onResume was called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("mylifecycle", "onPause was called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("mylifecycle", "onStop was called");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("mylifecycle", "onRestart was called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("mylifecycle", "onDestroy was called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("mylifecycle", "onSaveInstanceState called");
        outState.putString("name", textView.toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("mylifecycle", "onRestoreInstanceStae");
        String valueName = savedInstanceState.getString("name");
        textView.toString();
    }

    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String secProducts[];
        String secDescription[];

        MyAdapter(Context c, String products[], String description[]){
            super(c, R.layout.row, R.id.textview1, products);
            this.context = c;
            this.secProducts = products;
            this.secDescription = description;
        }

        @NonNull
        @Override

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Boolean attachToRoot = false;
            View row = layoutInflater.inflate(R.layout.row, parent, attachToRoot);
            TextView myProducts = row.findViewById(R.id.textview1);
            TextView myDescription = row.findViewById(R.id.textview2);

            myProducts.setText(secProducts[position]);
            myDescription.setText(secDescription[position]);

            return row;
        }

    }
}
