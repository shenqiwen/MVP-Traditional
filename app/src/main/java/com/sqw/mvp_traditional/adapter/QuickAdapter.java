package com.sqw.mvp_traditional.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;

/**
 * RecycleView Adapter 辅助类
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */

public class QuickAdapter extends BaseQuickAdapter<MultiNewsArticleDataBean, BaseViewHolder>{

    public QuickAdapter() {
        super(R.layout.item_news_article_text);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MultiNewsArticleDataBean item) {
        viewHolder.setText(R.id.tv_time, item.getTime())
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent());
       // Glide.with(mContext).load(item.picaddr).into((ImageView) viewHolder.getView(R.id.lmi_avatar));
    }
}
