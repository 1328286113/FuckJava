package com.example.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.aidlserver.Book
import com.example.aidlserver.BookController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val TAG = "Client"

    private var bookController: BookController? = null

    private var connected = false

    private var bookList: List<Book>? = null

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            bookController = BookController.Stub.asInterface(service)
            connected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            connected = false
        }
    }

    private val clickListener: View.OnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn_getBookList -> if (connected) {
                try {
                    bookList = bookController?.bookList
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
                log()
            }
            R.id.btn_addBook_inOut -> if (connected) {
                val book = Book("这是一本新书 InOut")
                try {
                    bookController?.addBookInOut(book)
                    Log.e(TAG, "向服务器以InOut方式添加了一本新书")
                    Log.e(TAG, "新书名：" + book.name)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_getBookList.setOnClickListener(clickListener);
        btn_addBook_inOut.setOnClickListener(clickListener);
        bindService();
    }

    override fun onDestroy() {
        super.onDestroy()
        if (connected) {
            unbindService(serviceConnection)
        }
    }

    private fun bindService() {
        val intent = Intent()
        intent.setPackage("com.example.aidlserver")
        intent.action = "com.example.aidlserver.action"
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }


    private fun log() {
        for (book in bookList!!) {
            Log.e(TAG, book.name.toString())
        }
    }
}
