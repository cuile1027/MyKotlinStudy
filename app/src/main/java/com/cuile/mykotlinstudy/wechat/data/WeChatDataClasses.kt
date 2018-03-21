package com.cuile.mykotlinstudy.wechat.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by cuile on 2018/3/20.
 *
 */
data class WeChatInfo(val result: WeChatInfoResult, val reason: String, val error_code: Int)
data class WeChatInfoResult(val list: Array<WeChatInfoResultData>, val totalPage: Int, val ps: Int, val pno: Int)
data class WeChatInfoResultData(val id: String, val title: String, val source: String, val firstImg: String, val mark: String, val url: String) : Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(title)
        dest?.writeString(source)
        dest?.writeString(firstImg)
        dest?.writeString(mark)
        dest?.writeString(url)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<WeChatInfoResultData> {
        override fun createFromParcel(parcel: Parcel): WeChatInfoResultData {
            return WeChatInfoResultData(parcel)
        }

        override fun newArray(size: Int): Array<WeChatInfoResultData?> {
            return arrayOfNulls(size)
        }
    }
}