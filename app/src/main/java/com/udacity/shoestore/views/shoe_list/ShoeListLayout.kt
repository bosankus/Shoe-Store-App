package com.udacity.shoestore.views.shoe_list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LayoutShoeListItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListLayout : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding: LayoutShoeListItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_shoe_list_item,
            this,
            false
        )

    fun loadShoe(shoe: Shoe) {
        binding.apply {
            addView(this.root)
            shoeListItemName.text = shoe.name
            shoeListItemCompany.text = shoe.company
            shoeListItemSize.text = shoe.size.toString()
            shoeListItemDescription.text = shoe.description
        }
    }
}