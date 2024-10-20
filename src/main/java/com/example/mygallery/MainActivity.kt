package com.example.mygallery

import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    private var imageList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        // Load images (call your loadImages function here)
        loadImages()

        // Set up the adapter
        imageAdapter = ImageAdapter(imageList)
        recyclerView.adapter = imageAdapter
    }

    private fun loadImages() {
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                imageList.add(cursor.getString(columnIndex))
            }
            cursor.close()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadImages()
        }
    }
}

