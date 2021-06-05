package hcmute.edu.vn.mssv.ChungThienNhi18110330.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) %2 != 0){
            outRect.top = 50;
            outRect.bottom = -50;
        }
    }
}
