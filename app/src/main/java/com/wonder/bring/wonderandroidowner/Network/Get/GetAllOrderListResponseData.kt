package com.wonder.bring.wonderandroidowner.Network.Get

data class GetAllOrderListResponseData(
    var status: Int,
    var message: String,
    var data: OrderListData
)