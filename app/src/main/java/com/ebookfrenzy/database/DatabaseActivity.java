package com.ebookfrenzy.database;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseActivity extends ActionBarActivity {

    TextView idview;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idview = (TextView)findViewById(R.id.productID);
        productBox = (EditText)findViewById(R.id.productName);
        quantityBox = (EditText)findViewById(R.id.productQuantity);

    }

    public void newProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        String proname = productBox.getText().toString();
        int quantity = Integer.parseInt(quantityBox.getText().toString());

        Product product = new Product(proname,quantity);

        dbHandler.addProduct(product);
        productBox.setText("");
        quantityBox.setText("");
        Toast.makeText(this,proname+" insert complete!",Toast.LENGTH_SHORT).show();
    }

    public void lookupProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        String proname = productBox.getText().toString();
        Product product = dbHandler.findProduct(productBox.getText().toString());

        if(product!=null){
            idview.setText(String.valueOf(product.getID()));
            quantityBox.setText(String.valueOf(product.getQuantity()));
            Toast.makeText(this,proname+" find complete!",Toast.LENGTH_SHORT).show();
        }else {
            idview.setText("No match Found");
            Toast.makeText(this,proname+" find fail !",Toast.LENGTH_SHORT).show();
        }
    }

    public void removeProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        String proname = productBox.getText().toString();
        boolean result = dbHandler.deleteProduct(productBox.getText().toString());

        if(result)
        {
            idview.setText("Record Deleted");
            productBox.setText("");
            quantityBox.setText("");
            Toast.makeText(this,proname+" delete complete!",Toast.LENGTH_SHORT).show();
        }
        else {
            idview.setText("No match Found");
            Toast.makeText(this,proname+" delete fail !",Toast.LENGTH_SHORT).show();
        }
    }

    public void changeProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        String proname = productBox.getText().toString();
        String pattern = "->";
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(proname);
        boolean matches = mat.find();

        if(matches) {
            String basename = proname.split("->")[0];
            String aftername = proname.split("->")[1];

            boolean result = dbHandler.settingProduct(basename,aftername);

            if(result) {
                Toast.makeText(this,"changed complete !",Toast.LENGTH_LONG).show();
                productBox.setText("");
                quantityBox.setText("");
            }
            else {
                Toast.makeText(this,"changed fail !",Toast.LENGTH_LONG).show();
                productBox.setText("");
                quantityBox.setText("");
            }
        }
        else {
            Toast.makeText(this,"not found \"->\" regulation! ",Toast.LENGTH_LONG).show();
            productBox.setText("");
            quantityBox.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_database, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
