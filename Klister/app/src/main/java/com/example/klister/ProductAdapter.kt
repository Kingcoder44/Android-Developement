package com.example.klister

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProductAdapter(
    private val context: Context,
    private val listOfProduct: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductVH>() {

    inner class ProductVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val proImg: ImageView = itemView.findViewById(R.id.img)
        val proName: TextView = itemView.findViewById(R.id.pro_name)
        val proPrice: TextView = itemView.findViewById(R.id.pro_pri)
        val proDes: TextView = itemView.findViewById(R.id.pro_des)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product, parent, false)
        return ProductVH(view)
    }

    override fun getItemCount(): Int {
        return listOfProduct.size
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val currPro = listOfProduct[position]
        holder.proName.text = currPro.Pro_name
        holder.proPrice.text = currPro.Pro_Price
        holder.proDes.text = currPro.Pro_Des

        // Use Glide to load the product image with error handling and placeholder
        Glide.with(context)
            .load(currPro.Pro_Img)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder) // Placeholder image
                    .error(R.drawable.error_placeholder)  // Error image
            )
            .into(holder.proImg)
    }
}
