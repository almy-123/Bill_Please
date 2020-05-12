package sg.edu.rp.c346.id19037610.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    EditText amount, pax;
    ToggleButton service, gst;
    EditText discount;
    TextView dBill, dEachPay;
    Button split, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.txtAmt);
        pax = findViewById(R.id.txtPax);
        service = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        discount = findViewById(R.id.txtDis);
        dBill = findViewById(R.id.disBill);
        dEachPay = findViewById(R.id.disEachPay);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"Cannot leave Amount empty!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(pax.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"Cannot leave Pax empty!",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                double amt = Double.parseDouble(amount.getText().toString().trim());
                if(service.isChecked() && gst.isChecked()){
                    amt = amt*1.17;
                }else if(service.isChecked() && !gst.isChecked()){
                    amt = amt*1.1;
                }else if(!service.isChecked() && gst.isChecked()){
                    amt = amt*1.07;
                }

                if(discount.getText().toString().trim().length()>0){
                    double dis = Double.parseDouble(discount.getText().toString().trim());
                    amt = amt*(1-dis/100);
                }

                dBill.setText(String.format("$%.2f",amt));
                double ppl = Double.parseDouble(pax.getText().toString().trim());
                dEachPay.setText(String.format("$%.2f",amt/ppl));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                service.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                dBill.setText("");
                dEachPay.setText("");
            }
        });
    }
}
