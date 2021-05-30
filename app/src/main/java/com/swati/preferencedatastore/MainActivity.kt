package com.swati.preferencedatastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var userPreferences: PreferenceDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPreferences = PreferenceDataStore(this)

        buttonSaveBookmark.setOnClickListener {
            val bookmarkKey = editTextBookmarkKeyInput.text.toString().trim()
            val bookmark = editTextBookmark.text.toString().trim()

            lifecycleScope.launch {
                editTextBookmarkKeyInput.text.clear()
                editTextBookmark.text.clear()
                userPreferences.saveBirthday(bookmarkKey, bookmark)
            }

        }

        buttonGetBookmark.setOnClickListener {
            val bookmarkKey = editTextBookmarkKey.text.toString().trim()

            lifecycleScope.launch {
                editTextBookmarkKey.text.clear();
                val read = userPreferences.getBirthday(bookmarkKey)
                if (read.isNullOrEmpty()) {
                    textViewCurrentBookmark.text = getString(R.string.save_details_first)
                } else {
                    textViewCurrentBookmark.text = read
                }
            }

        }
    }
}