package com.ericrgon.collapsingtoolbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.background) ImageView banner;
  @InjectView(R.id.overlay) View overlay;
  @InjectView(android.R.id.list) RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);

    /**
     * The toolbar is contained within the activity layout.
     */
    toolbar.setTitle(R.string.title_collapsing_toolbar);

    ListAdapter adapter = new ListAdapter();
    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        View headerPadding = recyclerView.getChildAt(0);
        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {

          int y = (int) headerPadding.getY();
          int toolBarY = -(banner.getBottom() - toolbar.getHeight());
          float percent = -y / (float) (banner.getHeight() - toolbar.getHeight());

          banner.setY(Math.max(y, toolBarY));
          overlay.setY(Math.max(y, toolBarY));
          overlay.setAlpha(percent);
        }
      }
    });
  }
}
