package br.com.lucasaquiles.cogg.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasaquiles.cogg.R;
import br.com.lucasaquiles.cogg.bean.Sketche;

/**
 * Created by lucasaquiles on 15/11/16.
 */

public class ResumoListAdapter extends ArrayAdapter {


    private int layoutResourceId;
    private Context context;
    private List data = new ArrayList();


    public ResumoListAdapter(Context context, int layoutResourceId, List data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View row = convertView;
        ViewHolder holder = null;


        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ResumoListAdapter.ViewHolder();

            holder.imageTitleTextView = (TextView) row.findViewById(R.id.title);
            holder.emocacaoTextView = (TextView) row.findViewById(R.id.emocao);
            holder.data = (TextView) row.findViewById(R.id.data);

            holder.image = (ImageView) row.findViewById(R.id.image);

            row.setTag(holder);
        } else {
            holder = (ResumoListAdapter.ViewHolder) row.getTag();
        }

        final Sketche item = (Sketche) data.get(position);
        holder.imageTitleTextView.setText(item.getPic().getTitle()+" "+item.getId());
        holder.emocacaoTextView.setText(item.getPic().getEmotion());
        Drawable draw = Drawable.createFromPath(item.getPathToAvatar());

        if(item.getData() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = dateFormat.format(item.getData());

            holder.data.setText("criado em: " + date);
        }

        if (draw != null && draw instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
            if (bitmapDrawable.getBitmap() != null) {

                Bitmap bitmap = bitmapDrawable.getBitmap();
                holder.image.setImageBitmap(bitmap);
            }
        }



        return row;
    }


    static class ViewHolder {
        TextView imageTitleTextView;
        TextView emocacaoTextView;
        ImageView image;
        TextView data;

    }

}
