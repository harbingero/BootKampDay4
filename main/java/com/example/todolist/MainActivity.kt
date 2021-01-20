package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var todoItemRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: TodoItemsAdapter
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    var todoItemsList = ArrayList<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tasks to accomplish:
        // Build the main activity UI
        // Build an add item page UI
        // Adding connections from layout to activity files
        // Adding listeners to buttons and implementing navigation
        // Implement todolist functionality
        // Create the custom layout to represent an item
        // Implement pre database set up
        // Implement on click listener
        // Implement pre database set up
        // Create a database schema
        // Implement database functionality
        // Implement all database functions
        // Implement database functionality
        // Call database functions in the appropriate locations

        val dbo = DatabaseOperations(this)
        val cursor = dbo.getAllItems(dbo)
        with(cursor) {
            while(moveToNext()) {
                val itemName = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME))
                val itemUrgency = getInt(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_URGENCY))
                val isUrgent = if (itemUrgency == 0) false else true
                todoItemsList.add(TodoItem(itemName, isUrgent))
            }
        }

//        todoItemsList.add(TodoItem("Buy Groceries", false))
//        todoItemsList.add(TodoItem("Do Laundry", true))
//        todoItemsList.add(TodoItem("Play Guitar", false))

        todoItemRecyclerView = findViewById(R.id.todo_item_recycler_view)


        recyclerLayoutManager = LinearLayoutManager(this)
        recyclerAdapter = TodoItemsAdapter(todoItemsList, this)

        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }

    }

    public fun navToAddItemAction(view: View) {
        val intent: Intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }
}