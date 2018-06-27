package com.example.comicbook.mvp.presenter;

import com.example.comicbook.mvp.contract.BookListContract;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;

public class BookListPresenter extends BasePresenter<BookListContract
        .IBookView> implements BookListContract
        .IBookPresenter{
}
