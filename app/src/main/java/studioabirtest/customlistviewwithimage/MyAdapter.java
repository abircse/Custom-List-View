package studioabirtest.customlistviewwithimage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class MyAdapter extends ArrayAdapter<WeModel> {

    private ArrayList<WeModel> books;
    private Context context;
    private int resource;

    public MyAdapter(Context context,int resource,ArrayList<WeModel> books) {
        super(context, resource, books);
        this.books = books;
        this.context = context;
        this.resource = resource;
    }
    
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)
                    getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layoutdemo, null, true);
        }

        WeModel weModel = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(context).load(weModel.getImage()).into(imageView);

        TextView textView1 = (TextView) convertView.findViewById(R.id.mytext1);
        textView1.setText(weModel.getName());

        TextView textView2 = (TextView) convertView.findViewById(R.id.mytext2);
        textView2.setText(weModel.getDate());

        return convertView;
    }
}
