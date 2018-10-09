package com.example.oxygen.m;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class customListAdapter extends BaseAdapter {
    private List<detail> danhsach;
    private LayoutInflater layoutInflater;
    private Context context;

    public customListAdapter() {

    }

    public customListAdapter(Context sContext, List<detail> sDanhSach) {
        this.context = sContext;
        this.danhsach = sDanhSach;
        layoutInflater = LayoutInflater.from(sContext);
    }

    @Override
    public int getCount() {
        return danhsach.size();
    }

    @Override
    public Object getItem(int position) {
        return danhsach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_img);
            holder.country = (TextView) convertView.findViewById(R.id.item_city);
            holder.date = (TextView) convertView.findViewById(R.id.item_date);
            holder.date = (TextView) convertView.findViewById(R.id.item_temp);
            holder.humidity = (TextView) convertView.findViewById(R.id.item_humidity);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        detail item = this.danhsach.get(position);
        holder.country.setText(item.getTenThanhPho());
        holder.date.setText("Ngày: "+item.getTime_update());
        holder.temp.setText("Nhiệt độ: " + item.getNhietdo());
        holder.humidity.setText("Độ ẩm: " + item.getDoAm());
        int image = this.findImageId(item.getImg());

        holder.imageView.setImageResource(image);
        return convertView;
    }

    //Tìm id của ảnh trong ứng dụng
    public int findImageId(String name)
    {
        String sName = context.getPackageName();

        int respondent = context.getResources().getIdentifier(name, "drawable", sName);
        Log.i("CustomListView", "Name: "+name+ "==> respondentID: " + respondent);
        return respondent;
    }
    static class ViewHolder{
        ImageView imageView;
        TextView country;
        TextView date;
        TextView temp;
        TextView humidity;
    }
}

