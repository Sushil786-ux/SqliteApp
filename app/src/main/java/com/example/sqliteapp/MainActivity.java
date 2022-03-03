package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper mydb;
    TextView nm,ph,pl;
    EditText names,phones,places;
    Button add;
    ListView lists;
    ArrayList<String> listitem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DataBaseHelper(this);
        names=findViewById(R.id.names);
        places=findViewById(R.id.places);
        phones=findViewById(R.id.phones);
        add=findViewById(R.id.add);
        lists=findViewById(R.id.lists);

        listitem=new ArrayList<>();

        viewData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isinserted=mydb.insertData(names.getText().toString(),phones.getText().toString(),places.getText().toString());
                if (isinserted==true)
                {
                    listitem.clear();
                    viewData();
                    Toast.makeText(getApplicationContext(),"Submit Successfully"+names.getText().toString(),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        updatedatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readitemtextview.setText("");
                readqtyitemview.setText("");
                readid.setText("");
                String id = updateid.getText().toString();
                String item = updateitem.getText().toString();
                int qty = Integer.parseInt(updateqty.getText().toString());
                updateid.setText("");
                updateitem.setText("");
                updateqty.setText("");


                if (!id.isEmpty() && !item.isEmpty()){

                    if(mydb.updateData(id,item,qty)){

                        Toast.makeText(MainActivity.this,"Data updated Successfuly",Toast.LENGTH_LONG).show();

                    }else {

                        Toast.makeText(MainActivity.this,"Error in updation",Toast.LENGTH_LONG).show();
                    }

                }


            }
        });
        deletedatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readid.setText("");
                readqtyitemview.setText("");
                readitemtextview.setText("");
                updateid.setText("");
                updateqty.setText("");
                updateitem.setText("");
                writeid.setText("");
                writeitem.setText("");
                writeqty.setText("");
                String id = deleteid.getText().toString();
                if (!id.isEmpty()){

                    if(mydb.deleteData(id) > 0){

                        Toast.makeText(MainActivity.this,"Sucessfuly deleted",Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

    }
    private void viewData(){
                Cursor res=mydb.getAllData();
                if (res.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Data Available",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    while(res.moveToNext()){
                        listitem.add(res.getString(1));
                    }
                    adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listitem);
                    lists.setAdapter(adapter);
                }
            }
}