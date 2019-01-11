package com.wonder.bring.wonderandroidowner.Network.Get

import java.util.*

data class OrderListData(
    var orderListIdx: Int,
    var nick: String,
    var state: Int,
    var time: String,
    var totalPrice: Int,
    var totalCount: Int,
    var firstMenu: FirstMenu
)

//
//var date: String,
//var time: String,
//var orderNum: Int,
//var nickname: String,
//var menu: String,
//var cost: Int,
//var request: String
//)
