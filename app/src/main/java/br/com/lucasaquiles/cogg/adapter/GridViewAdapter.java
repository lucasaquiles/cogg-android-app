package br.com.lucasaquiles.cogg.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import br.com.lucasaquiles.cogg.PlayActivity;
import br.com.lucasaquiles.cogg.R;
import br.com.lucasaquiles.cogg.ResumoActivity;
import br.com.lucasaquiles.cogg.view.CustomButtton;
import br.com.lucasaquiles.cogg.view.CustomTextView;
import br.com.lucasaquiles.cogg.view.ImageConfigActivity;
import br.com.lucasaquiles.cogg.view.ImageItem;

/**
 * Created by lucasaquiles on 1/4/16.
 */
public class GridViewAdapter extends ArrayAdapter {
    private int layoutResourceId;
    private Context context;
    private ArrayList data = new ArrayList();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();

            holder.imageTitle = (CustomTextView ) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            holder.chooseButton  = (CustomButtton) row.findViewById(R.id.buttonChooseImage);

            holder.configImage  = (CustomButtton) row.findViewById(R.id.buttonConfigImage);
            holder.verColagens  = (CustomButtton) row.findViewById(R.id.verColagens);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        final ImageItem item = (ImageItem) data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());

        holder.verColagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ResumoActivity.class);
                intent.putExtra("pic", item.getPic());
                context.startActivity(intent);
            }
        });

        holder.chooseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, PlayActivity.class);

                    if(item.getResourceId() > 0) {
                        intent.putExtra("byteArray", item.getResourceId());
                    }else {
                        intent.putExtra("filePath", item.getFilePath());
                        intent.putExtra("title", item.getTitle());
                        intent.putExtra("pic", item.getPic());
                        intent.putExtra("config", false);
                    }

                    context.startActivity(intent);
                }
            });
            holder.configImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context.getApplicationContext(), ImageConfigActivity.class);
                    i.putExtra("filePath",  item.getFilePath());
                    i.putExtra("titulo",  item.getPic().getTitle());
                    i.putExtra("emocao",  item.getPic().getEmotion());
                    i.putExtra("pic", item.getPic());


                    context.startActivity(i);

                }
        });

        return row;
    }

    static class ViewHolder {
        CustomTextView  imageTitle;
        ImageView image;
        CustomButtton chooseButton;
        CustomButtton configImage;
        CustomButtton verColagens;
    }

}
