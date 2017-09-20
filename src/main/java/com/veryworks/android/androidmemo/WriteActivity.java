package com.veryworks.android.androidmemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.veryworks.android.androidmemo.domain.Memo;
import com.veryworks.android.androidmemo.util.FileUtil;

import java.io.IOException;

public class WriteActivity extends AppCompatActivity {

    private Button btnPost;
    private EditText editTitle;
    private EditText editAuthor;
    private EditText editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        initView();
        initListener();
    }

    /**
     * 내용을 파일에 작성
     * - 파일쓰기
     *   내부저장소 - Internal : 개별앱만 접근가능, 파일탐색기에서 보이지 않는다.
     *   외부저장소 - External : 모든앱이 접근가능 > 권한 필요
     */
    private Memo getMemoFromScreen(){ // 메모클래스에서 객체를 받아옴. // 메모의 변수들에 값들을 세팅해서, 메모객체로 리턴한다.
        Memo memo = new Memo();       // 스크린으로부터 메모를 받아옴. 메모객체에 있는 값들인 editText타입에 getText.toString을 해준다.
        memo.setNo(1);
        memo.setTitle(editTitle.getText().toString());
        memo.setAuthor(editAuthor.getText().toString());
        memo.setContent(editContent.getText().toString());
        memo.setDatetime(System.currentTimeMillis());
        return memo;
    }

    private void write(Memo memo){//write 메소드에 메모객체를 인자로 넣는다.
        try {
            String filename = System.currentTimeMillis() + ".txt"; //filename변수에 현재 시간을 담는다.
            FileUtil.write(this, filename, memo.toString());

            setResult(RESULT_OK); // 나를 호출한 액티비티로 성공/실패 값을 넘겨준다.

            Toast.makeText(this, "등록되었습니다!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "에러:"+e.toString(), Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private void initListener() {
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//클릭시에
                Memo memo = getMemoFromScreen(); // getMemoFromScreen메서드를 memo객체에 담는다?
                write(memo); // 메모를 write메서드에 담는다.
            }
        });
    }

    private void initView() {
        btnPost = (Button) findViewById(R.id.btnPost);
        editTitle = (EditText) findViewById(R.id.editTitle);
        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editContent = (EditText) findViewById(R.id.editContent);
    }
}
