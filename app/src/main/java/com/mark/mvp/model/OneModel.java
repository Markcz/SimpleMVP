package com.mark.mvp.model;

import java.util.ArrayList;

/**
 * Created by mark on 18-4-29.
 */

public class OneModel implements IModel {
    @Override
    public void loadData(DataLoadListener listener) {
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0;i < 1000;i++){
            datas.add(i+"");
        }
        if (listener != null){
            if (datas.size() > 0){
                listener.onComplete(datas);
            }else {
                listener.onFailure("未获取到数据");
            }

        }
    }
}
