package com.sas.hebbarscoffee;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class OrderActivity extends AppCompatActivity {
  int quantity=0;
  int totalCost=0;
  Button confirmButton;
  EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String coffee = intent.getStringExtra("coffee");
        String cost = intent.getStringExtra("cost");
        totalCost=Integer.parseInt(cost);
        TextView beverageName=findViewById(R.id.beverageName);
        beverageName.setText(""+coffee);

        editText=findViewById(R.id.address);
        confirmButton=findViewById(R.id.confirm);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quantity<=0)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please check the quantity!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    String userAddress=editText.getText().toString();


                    if(userAddress.matches(""))
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Fill in all the fields!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    else
                    openDialog();

                }
            }
        });
    }
    public void openDialog()
    {
       Dialog dialog=new Dialog();
       dialog.show(getSupportFragmentManager(),"Confirmation dialog");

    }



   public void increment(View view) {
    quantity=quantity+1;
    display(quantity);
    }

    public void decrement(View view) {
        quantity=quantity-1;
        display(quantity);
    }

    @SuppressLint("SetTextI18n")
    public void display(int quantity)
    {
        TextView quantityValue=findViewById(R.id.quantity);
        quantityValue.setText(""+quantity);
        displayAmount(quantity,totalCost);

    }

    @SuppressLint("SetTextI18n")
    private void displayAmount(int quantity, int cost) {
        TextView amount=findViewById(R.id.totalAmount);
        amount.setText(""+(quantity*cost));
    }


}
