package com.wonder.bring.wonderandroidowner.Network

import com.wonder.bring.wonderandroidowner.Network.Get.GetAllOrderListResponseData
import com.wonder.bring.wonderandroidowner.Network.Get.GetChangeOrderStatusResponseData
import com.wonder.bring.wonderandroidowner.Network.Get.GetOrderDetailResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    //모든 주문 내역 조회
    @GET("/stores/{storeIdx}/orderLists")
    fun getAllOrderListRequest(
        @Header("Content-Type") content_type: String,
        @Path("StoreIdx") storeIdx: Int
    ): Call<GetAllOrderListResponseData>

    //주문 상세 내역 조회
    @GET("/stores/{storeIdx}/orderLists/{orderIdx}")
    fun getOrderDetailRequest(
        @Header("Content-Type") content_type: String,
        @Path("storeIdx") storeIdx: Int,
        @Path("orderIdx") orderIdx: Int
    ): Call<GetOrderDetailResponseData>

    //주문서 상태 바꾸기
    @GET("/orderLists/{orderIdx}")
    fun getChangeOrderStatusRequest(
        @Header("Content-Type") content_type: String,
        @Path("orderIdx") orderIdx: Int,
        @Query("state") state: Int
    ): Call<GetChangeOrderStatusResponseData>
}

//  value   설명
//  -----------------
//  1	    주문 접수
//  2	    제조 완료
//  3	    수령
//  4	    주문 거절
