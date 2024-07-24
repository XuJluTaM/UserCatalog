package com.example.usercatalog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var userNameInput: TextInputEditText
    private lateinit var userAgeInput: TextInputEditText
    private lateinit var userListView: ListView
    private val userList = mutableListOf<User>()
    private lateinit var userAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Каталог пользователей"

        userNameInput = findViewById(R.id.user_name_input)
        userAgeInput = findViewById(R.id.user_age_input)
        userListView = findViewById(R.id.user_list_view)

        userAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        userListView.adapter = userAdapter

        findViewById<Button>(R.id.save_button).setOnClickListener {
            saveUser()
        }

        userListView.setOnItemClickListener { _, _, position, _ ->
            removeUser(position)
        }
    }

    private fun saveUser() {
        val name = userNameInput.text.toString()
        val age = userAgeInput.text.toString().toIntOrNull()

        if (name.isNotBlank() && age != null) {
            val user = User(name, age)
            userList.add(user)
            userAdapter.add("${user.name}, ${user.age}")
            userNameInput.text?.clear()
            userAgeInput.text?.clear()
        }
    }

    private fun removeUser(position: Int) {
        userList.removeAt(position)
        userAdapter.remove(userAdapter.getItem(position))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
