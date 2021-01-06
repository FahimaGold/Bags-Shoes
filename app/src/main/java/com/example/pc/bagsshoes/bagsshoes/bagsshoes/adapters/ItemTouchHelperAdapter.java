package com.example.pc.bagsshoes.bagsshoes.bagsshoes.adapters;

import com.example.pc.bagsshoes.bagsshoes.bagsshoes.model.Product;

public interface ItemTouchHelperAdapter {
    void removeItem(int position);
    void restoreItem(Product product, int position);
}
