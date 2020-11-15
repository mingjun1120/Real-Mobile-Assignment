package com.example.warehouselimmingjun.data

import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.model.Product

class Datasource {
    fun loadProducts(): List<Product> {
        return listOf<Product>(
            Product(R.string.product1, R.drawable.image1),
            Product(R.string.product1, R.drawable.image1)
        )
    }
}