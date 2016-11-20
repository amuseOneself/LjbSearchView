package com.ljb.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljb.search.R;
import com.ljb.search.bean.UserInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListviewItemAdapter extends BaseAdapter {
    Context context;
    private List<UserInfo> users;
    private LayoutInflater layoutInflater;

    public ListviewItemAdapter(Context context, List<UserInfo> users) {
        this.context = context;
        this.users = users;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public UserInfo getItem(int position) {

        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(UserInfo userInfo, ViewHolder holder) {
        holder.id.setText("id: "+userInfo.getId());
        holder.name.setText("name: "+userInfo.getName());
        Picasso.with(context).load(userInfo.getAvatar_url()).into(holder.image);
    }

    protected class ViewHolder {
        private ImageView image;
        private TextView id;
        private TextView name;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
        }
    }

}
