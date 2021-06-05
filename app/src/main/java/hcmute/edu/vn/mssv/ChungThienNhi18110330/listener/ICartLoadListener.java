package hcmute.edu.vn.mssv.ChungThienNhi18110330.listener;

import java.util.List;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.CartModel;

public interface ICartLoadListener {
    void onCartSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);
}
