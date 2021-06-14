// Generated code from Butter Knife. Do not modify!
package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class test_ViewBinding implements Unbinder {
  private test target;

  @UiThread
  public test_ViewBinding(test target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public test_ViewBinding(test target, View source) {
    this.target = target;

    target.recyclerDrink = Utils.findRequiredViewAsType(source, R.id.recycler_drink, "field 'recyclerDrink'", RecyclerView.class);
    target.drawerLayout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawerLayout'", DrawerLayout.class);
    target.notification = Utils.findRequiredViewAsType(source, R.id.notification, "field 'notification'", ImageView.class);
    target.cart = Utils.findRequiredViewAsType(source, R.id.cart, "field 'cart'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    test target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerDrink = null;
    target.drawerLayout = null;
    target.notification = null;
    target.cart = null;
  }
}
