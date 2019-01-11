package com.wonder.bring.wonderandroidowner.Network.Get

data class GetOrderDetailResponseData(
    var status: Int,
    var message: String,
    var data: ArrayList<OrderDetail>
)