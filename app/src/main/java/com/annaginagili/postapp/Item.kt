package com.annaginagili.postapp

class Item {
    var title: String? = null
    var body: String? = null
    var id: Int? = null

    companion object {
        fun getData(titleList: ArrayList<String>, bodyList: ArrayList<String>, idList: ArrayList<Int>): ArrayList<Item> {
            val itemList = ArrayList<Item>()
            for (i in 0 until  titleList.size) {
                val item = Item()
                item.title = titleList[i]
                item.body = bodyList[i]
                item.id = idList[i]
                itemList.add(item)
            }
            return itemList
        }
    }
}