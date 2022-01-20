package com.example.ahmed.project6_1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NewsAdapter extends ArrayAdapter<News> {

     static class ViewHolder {

        private TextView sectionTextView;
        private TextView authorTextView;
        private TextView titleTextView;
        private TextView dateTextView;
        private TextView timeTextView;
    }

public NewsAdapter(Context context, ArrayList<News> object)
{
        super(context, 0, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        // Check if the existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.sectionTextView = convertView.findViewById(R.id.section);
            holder.authorTextView = convertView.findViewById(R.id.author);
            holder.titleTextView = convertView.findViewById(R.id.title);
            holder.dateTextView = convertView.findViewById(R.id.date);
            holder.timeTextView = convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        News currentNews = getItem(position);

        // Create a new Date object from the time in ISO-8601 format of the news.
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(currentNews.getPublicationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);

        // Set proper data in list_item by using ViewHolder.
        holder.sectionTextView.setText(currentNews.getSectionName());
        holder.authorTextView.setText(currentNews.getAuthor());
        holder.titleTextView.setText(currentNews.getTitle());
        holder.dateTextView.setText(formattedDate);
        holder.timeTextView.setText(formattedTime);

        return convertView;
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        timeFormat.setTimeZone(TimeZone.getDefault());
        return timeFormat.format(dateObject);
    }
}
