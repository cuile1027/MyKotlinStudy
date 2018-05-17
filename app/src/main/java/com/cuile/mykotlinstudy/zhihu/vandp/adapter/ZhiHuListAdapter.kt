package com.cuile.mykotlinstudy.zhihu.vandp.adapter

import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cuile.mykotlinstudy.GlideApp
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.zhihu.data.ZhihuListItem
import org.jetbrains.anko.find

/**
 * Created by cuile on 18-5-15.
 *
 */
class ZhiHuListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ITEM_TYPE { ITEM_TYPE_HEAD, ITEM_TYPE_BODY }

    private var headView: View? = null
    private var items: MutableList<ZhihuListItem> = mutableListOf()
    /**
     * 设置头View
     */
    fun setHeadView(headView: View?) {
        this.headView = headView
        notifyItemInserted(0)
    }

    fun addDatas(items: MutableList<ZhihuListItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getRealPosition(holder: RecyclerView.ViewHolder): Int = if (headView == null) holder.layoutPosition else holder.layoutPosition - 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (headView != null && viewType == ITEM_TYPE.ITEM_TYPE_HEAD.ordinal) return ViewHolderHead(headView as View)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_zhihulist_body, parent, false)
        return ViewHolderItem(view)
    }

    override fun getItemCount(): Int = if (headView == null) items.size else items.size + 1


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolderHead -> {

            }
            is ViewHolderItem -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (headView == null) return ITEM_TYPE.ITEM_TYPE_BODY.ordinal
        else {
            if (position == 0) return ITEM_TYPE.ITEM_TYPE_HEAD.ordinal
        }
        return ITEM_TYPE.ITEM_TYPE_BODY.ordinal
    }

    fun clearAll() {
        items.clear()
        notifyDataSetChanged()
    }

    class ViewHolderHead(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.find(R.id.item_zhihulist_title)
        private val imgIV: ImageView = itemView.find(R.id.item_zhihulist_img)

        fun bind(zhihuListItem: ZhihuListItem) {
            with(zhihuListItem) {
                titleTV.text = title
                GlideApp.with(imgIV.context)
                        .load(image)
                        .centerCrop()
                        .into(imgIV)
            }
        }
    }
}