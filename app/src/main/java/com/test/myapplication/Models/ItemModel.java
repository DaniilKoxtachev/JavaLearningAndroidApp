package com.test.myapplication.Models;

import androidx.annotation.Nullable;

public class ItemModel {
    public int section, positionInList;

    public ItemModel(int section, int positionInList) {
        this.section = section;
        this.positionInList = positionInList;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean res = false;
        if (obj instanceof ItemModel) {
            res = section == ((ItemModel) obj).section && positionInList == ((ItemModel) obj).positionInList;
        }
        return res;
    }
}
