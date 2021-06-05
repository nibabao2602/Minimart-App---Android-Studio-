package hcmute.edu.vn.mssv.ChungThienNhi18110330.listener;

import java.util.List;

import hcmute.edu.vn.mssv.ChungThienNhi18110330.model.DrinkModel;

public interface IDrinkLoadListener {
    void onDrinkLoadSuccess(List<DrinkModel> drinkModelList);
    void onDrinkLoadFailed(String message);
}
