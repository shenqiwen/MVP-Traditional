package com.sqw.mvp_traditional.binder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;

import me.drakeet.multitype.ItemViewBinder;


public class NewsArticleTextViewBinder extends ItemViewBinder<MultiNewsArticleDataBean, NewsArticleTextViewBinder.ViewHolder> {

    private static final String TAG = "NewsArticleTextViewBind";

    @NonNull
    @Override
    protected NewsArticleTextViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_news_article_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NewsArticleTextViewBinder.ViewHolder holder, @NonNull final MultiNewsArticleDataBean item) {
        try {
            final Context context = holder.itemView.getContext();

            String tv_title = item.getTitle();
            String tv_content = item.getContent();
            String tv_time = item.getTime();
            String tv_type = item.getType();

            holder.tv_title.setText(tv_title);
            holder.tv_content.setText(tv_content);
            holder.tv_time.setText(tv_time);
            holder.tv_type.setText(tv_type);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"点击"+holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_time;
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_type;

        ViewHolder(View itemView) {
            super(itemView);
            this.tv_time = itemView.findViewById(R.id.tv_time);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_content = itemView.findViewById(R.id.tv_content);
            this.tv_type = itemView.findViewById(R.id.tv_type);
        }
    }
}
