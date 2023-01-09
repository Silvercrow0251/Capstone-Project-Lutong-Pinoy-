package com.example.dday.A_FoodAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dday.A_FoodAdapter.ItemsAdapter.ItemsAdapterVH
import com.example.dday.R
import kotlinx.android.synthetic.main.row_items.view.*

class ItemsAdapter
    (var clickListener: ClickListener)
    : RecyclerView.Adapter<ItemsAdapterVH>(), Filterable {

    var itemsModalList = ArrayList<ItemsModal>();
//  Add Search Feature
    var itemsModalListFilter = ArrayList<ItemsModal>();

    fun setData(itemsModalList: ArrayList<ItemsModal>){
        this.itemsModalList = itemsModalList;
//  Add Search Feature
        this.itemsModalListFilter = itemsModalList;
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapterVH {
        return ItemsAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.row_items, parent, false));
    }

    override fun getItemCount(): Int {
        return itemsModalList.size
    }

    override fun onBindViewHolder(holder: ItemsAdapterVH, position: Int) {

        val itemsModal = itemsModalList[position];
        holder.name.text = itemsModal.name
        holder.desc.text = itemsModal.desc
        holder.food.text = itemsModal.food
        holder.serve.text = itemsModal.serve
        holder.meal.text = itemsModal.meal
        holder.image.setImageResource(itemsModal.image)

        holder.itemView.setOnClickListener {
            clickListener.ClickedItem(itemsModal)
        }
    }

    class ItemsAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.tvName
        val desc = itemView.tvDesc
        val food = itemView.tvFood
        val serve = itemView.tvServe
        val meal = itemView.txt1breakfast
        val image = itemView.imageView
    }

    interface ClickListener{
        fun ClickedItem(itemsModal: ItemsModal)
    }
//  Add Search Feature
    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {

                val filterResults = FilterResults();
                if(charSequence == null || charSequence.length < 0){
                    filterResults.count = itemsModalListFilter.size
                    filterResults.values = itemsModalListFilter
                }else{

                    var searchChr = charSequence.toString().toLowerCase()

                    val itemModal = ArrayList<ItemsModal>()

                    for(item in itemsModalListFilter) {
                        if(item.name.contains(searchChr) || item.desc.contains(searchChr) || item.food.contains(searchChr)){
                            itemModal.add(item)
                        }
                    }
                    filterResults.count = itemModal.size
                    filterResults.values = itemModal
                }
                return filterResults;
            }
//  Add Search Feature
            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {

                itemsModalList = filterResults!!.values as ArrayList<ItemsModal>
                notifyDataSetChanged()
            }
        }
    }
}