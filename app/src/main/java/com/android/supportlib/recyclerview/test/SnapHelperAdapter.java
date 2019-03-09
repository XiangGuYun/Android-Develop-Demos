package com.android.supportlib.recyclerview.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView item 居中对齐 的 Adapter
 *
 * Created by Tnno Wu on 2018/03/07.
 */

public class SnapHelperAdapter extends RecyclerView.Adapter<SnapHelperAdapter.SnapHelperViewHolder> {

    private static final String TAG = SnapHelperAdapter.class.getSimpleName();

    private Context mContext;

    private List<String> mList = new ArrayList<>();

    public SnapHelperAdapter(Context context) {
        mContext = context;
    }

    public void setSnapHelperDataList(List<String> list) {
        mList = list;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SnapHelperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.snaphelper_recycle_item, parent, false);
        return new SnapHelperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SnapHelperViewHolder holder, int position) {
        holder.tvTeam.setText(mList.get(position));

        // 为了无限循环，防止数组越界，所以 position % mList.size()
//        holder.tvTeam.setText(mList.get(position % mList.size()));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();

        // 无限循环
//        return mList == null ? 0 : Integer.MAX_VALUE;
    }

    public class SnapHelperViewHolder extends RecyclerView.ViewHolder {

        TextView tvTeam;

        public SnapHelperViewHolder(View itemView) {
            super(itemView);
            //tvTeam = itemView.findViewById(R.id.tv_team);
        }
    }
}