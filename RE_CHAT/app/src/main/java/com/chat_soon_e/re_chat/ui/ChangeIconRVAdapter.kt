package com.chat_soon_e.re_chat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chat_soon_e.re_chat.data.entities.Icon
import com.chat_soon_e.re_chat.databinding.ItemIconBinding

class ChangeIconRVAdapter(private val iconList: ArrayList<Icon>): RecyclerView.Adapter<ChangeIconRVAdapter.ViewHolder>() {
    // 클릭 인터페이스
    interface MyItemClickListener {
        fun onIconClick(itemIconBinding: ItemIconBinding, itemPosition: Int)
    }

    // 리스너 객체를 저장하는 변수
    private lateinit var mItemClickListener: MyItemClickListener

    // 리스너 객체를 외부에서 전달받는 함수
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    // 뷰홀더를 생성해줘야 할 때 호출되는 함수
    // 아이템 뷰 객체를 만들어서 뷰홀더에 던져준다.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemIconBinding = ItemIconBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // 뷰홀더에 데이터 바인딩을 해줘야 할 때마다 호출되는 함수
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(iconList[position])

        // 아이콘 (중 하나를) 클릭했을 때
        holder.itemView.setOnClickListener {
            mItemClickListener.onIconClick(holder.binding, position)
        }
    }

    // 데아터셋의 크기 반환
    override fun getItemCount(): Int = iconList.size

    // 뷰홀더
    inner class ViewHolder(val binding: ItemIconBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(icon: Icon) {
            binding.itemIconIv.setImageResource(icon.iconImage)
        }
    }
}