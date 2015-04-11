package com.ericrgon.collapsingtoolbar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

  public static final int VIEW_TYPE_HEADER = 0;
  public static final int VIEW_TYPE_ITEM = 1;

  public static class HeaderPaddingViewHolder extends RecyclerView.ViewHolder {
    View root;

    public HeaderPaddingViewHolder(View itemView) {
      super(itemView);
      root = itemView;
    }
  }

  public static class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView itemText;

    public ItemViewHolder(View itemView) {
      super(itemView);
      itemText = (TextView) itemView.findViewById(android.R.id.text1);
    }
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (getItemViewType(viewType)){
      case VIEW_TYPE_HEADER:
      View header = LayoutInflater
          .from(parent.getContext())
          .inflate(R.layout.item_header_padding,parent,false);
      return new HeaderPaddingViewHolder(header);
      case VIEW_TYPE_ITEM:
        View item = LayoutInflater.from(parent.getContext())
            .inflate(android.R.layout.simple_list_item_1,parent,false);
        return new ItemViewHolder(item);
    }

    throw new IllegalArgumentException("Unknown view type.");
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    switch (getItemViewType(position)){
      case VIEW_TYPE_HEADER:
        break;
      case VIEW_TYPE_ITEM:
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.itemText.setText(String.format("List item %d",position));
        break;
    }
  }

  @Override public int getItemCount() {
    return 21;
  }

  @Override public int getItemViewType(int position) {
    return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
  }
}
