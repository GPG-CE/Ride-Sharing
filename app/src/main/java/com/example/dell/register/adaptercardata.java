package com.example.dell.register;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class adaptercardata extends ArrayAdapter<String> {

    private String[] to1;
    private String[] via1;
    private String[] via2;
    private String[] from1;
    private String[] date1;
    private String[] time1;
    private String[] facility;
    private String[] cr_no;
    private Activity context;

    public adaptercardata (Activity context , String[] to1 , String[] via1 , String[] via2 , String[] from1 , String[] date1 , String[] time1 , String[] facility ,String [] cr_no)
    {
        super(context , R.layout.searchdatalist , to1);
        this.to1 = to1;
        this.via1 = via1;
        this.via2 = via2;
        this.from1 = from1;
        this.date1 = date1;
        this.time1 = time1;
        this.facility = facility;
        this.cr_no = cr_no;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.searchdatalist,null,true);
        TextView textViewto1=(TextView)listViewItem.findViewById(R.id.to1);
        TextView textViewvia1=(TextView)listViewItem.findViewById(R.id.via1);
        TextView textViewvia2=(TextView)listViewItem.findViewById(R.id.via2);
        TextView textViewfrom1=(TextView)listViewItem.findViewById(R.id.from1);
        TextView textViewdate1=(TextView)listViewItem.findViewById(R.id.date1);
        TextView textViewtime1=(TextView)listViewItem.findViewById(R.id.time1);
        TextView textViewfacility1=(TextView)listViewItem.findViewById(R.id.facility);
        TextView textViewcar_no = (TextView)listViewItem.findViewById(R.id.cr_no);


        textViewto1.setText(to1[position]+"");
        textViewvia1.setText(via1[position]);
        textViewvia2.setText(via2[position]);
        textViewfrom1.setText(from1[position]+"");
        textViewdate1.setText(date1[position]);
        textViewtime1.setText(time1[position]);
        textViewfacility1.setText(facility[position]);
        textViewcar_no.setText(cr_no[position]);


        return  listViewItem;
    }

}
