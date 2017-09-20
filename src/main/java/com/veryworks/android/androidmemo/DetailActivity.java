package com.veryworks.android.androidmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView textTitle;
    private TextView textDate;
    private TextView textAuthor;
    private TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        init();
    }

    public void init(){
        Intent intent = getIntent(); // 리스트 어댑터에서 보낸 인텐트들을 받는다.
        int position = intent.getIntExtra("position", -1); // 나중에 사용할 것임.

        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String content = intent.getStringExtra("content");
        String datetime = intent.getStringExtra("datetime");

        textTitle.setText(title);
        textDate.setText(datetime);
        textAuthor.setText(author);
        textContent.setText(content);
    }

    private void initView() {
        textTitle = (TextView) findViewById(R.id.textTitle);
        textDate = (TextView) findViewById(R.id.textDate);
        textAuthor = (TextView) findViewById(R.id.textAuthor);
        textContent = (TextView) findViewById(R.id.textContent);
    }
}
