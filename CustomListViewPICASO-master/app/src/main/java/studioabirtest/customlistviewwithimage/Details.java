package studioabirtest.customlistviewwithimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        //---------------Receive data from Intent---------------//
        String a = getIntent().getStringExtra("imagedata");
        String b = getIntent().getStringExtra("namedata");
        String c = getIntent().getStringExtra("datedata");

        //-----------Initialize weiget------------------///
        TextView textdata1  = (TextView) findViewById(R.id.dtext1);
        TextView textdata2  = (TextView) findViewById(R.id.dtext2);
        ImageView imageurl = (ImageView) findViewById(R.id.dimage);



        //---------------set data to show-------------///
        Picasso.with(this).load(a).into(imageurl);
        textdata1.setText(b);
        textdata2.setText(c);



    }
}
