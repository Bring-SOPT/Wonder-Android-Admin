package com.wonder.bring.wonderandroidowner.Network.Get

data class OrderDetail(
    var menuName: String,
    var size: Int,
    var orderCount: Int,
    var menuCountPrice: Int,
    var memo: String
)