package zplay.zplay;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;

import static zplay.zplay.PermisionUtils.verifyStoragePermissions;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private Button bt;
    private SeekBar seek;
    private Thread th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏.隐藏状态
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        setContentView(R.layout.activity_main);

        bt  = findViewById(R.id.open_button);
//        seek = findViewById( R.id.aplayseek );
//        seek.setMax(1000);
//        seek.setOnSeekBarChangeListener(this);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ZPlay","open button click");
                //打开选择路径窗口
                Intent intent = new Intent();
                intent.setClass( MainActivity.this ,OpenUrl.class);
                startActivity( intent );
            }
        });

//        //启动播放进度线程
//        th = new Thread(this);
//        th.start();




        verifyStoragePermissions(this);
    }

}
