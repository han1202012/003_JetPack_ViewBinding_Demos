package kim.hsl.vb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kim.hsl.vb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    /**
     * 视图绑定类 对象
     */
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1 . 获取视图绑定类
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // 2 . 关联视图绑定类 与 Activity
        setContentView(binding.getRoot());

        // 3 . 使用视图绑定类设置
        binding.textView.setText("视图绑定 ( ViewBinding ) 示例");
    }
}
