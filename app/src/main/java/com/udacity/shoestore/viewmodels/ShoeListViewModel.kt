package com.udacity.shoestore.viewmodels

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.BR
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel(), Observable {

    private val registry = PropertyChangeRegistry()

    private var _listOfShoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    private var _isItemAdded = MutableLiveData(false)
    val isItemAdded: LiveData<Boolean> get() = _isItemAdded


    @get:Bindable
    var shoe = Shoe()
        set(value) {
            if (value != field) {
                field = value
                registry.notifyChange(this, BR.shoe)
            }
        }

    /*init {
        _listOfShoes.value = mutableListOf(SHOE_ONE, SHOE_TWO)
    }*/

    fun listOfShoes(): LiveData<MutableList<Shoe>> {
        Timber.i("List fetched")
        _isItemAdded.value = false
        return _listOfShoes
    }

    fun addShoes(item: Shoe?) {
        item?.let {
            _listOfShoes.value?.add(it)
            _isItemAdded.value = true
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }

    /*companion object {
        //Pre-Populated Shoes
        val SHOE_ONE =
            Shoe("Chuck Taylor", 10.5, "Converse", "Retro Athletic Shoe", listOf("shoe"))
        val SHOE_TWO =
            Shoe("Charged Assert 8", 12.0, "Under Armour", "Running Shoe", listOf("shoe"))
    }*/
}