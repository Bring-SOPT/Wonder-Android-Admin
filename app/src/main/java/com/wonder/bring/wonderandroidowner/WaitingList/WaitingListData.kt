package com.wonder.bring.wonderandroidowner.WaitingList

data class WaitingListData(
    var date : String,
    var time : String,
    var orderNum : Int,
    var nickname : String,
    var menu : String,
    var cost : Int,
    var request : String
)