package com.example.aidlserver;
import com.example.aidlserver.Book;

interface BookController {
    List<Book> getBookList();
    void addBookInOut(inout Book book);
}
