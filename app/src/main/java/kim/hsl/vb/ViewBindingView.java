package kim.hsl.vb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import kim.hsl.vb.databinding.MyViewBinding;

public class ViewBindingView extends LinearLayout {

    /**
     * 在代码中创建组件调用该构造函数
     * @param context
     */
    public ViewBindingView(Context context) {
        super(context);
    }

    /**
     * 在 xml 布局文件中使用该组件 , 并且还定义了自定义属性 , 调用该构造函数
     * @param context
     * @param attrs
     */
    public ViewBindingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // 1 . 获取布局加载器
        LayoutInflater inflater = LayoutInflater.from(this.getContext());

        // 2 . 获取视图绑定类
        //     需要将视图绑定类 与 本自定义 LinearLayout 进行关联
        MyViewBinding binding = MyViewBinding.inflate(inflater, this, true);

        // 3 . 通过视图绑定类访问布局中的 TextView 布局
        binding.textView.setText("视图绑定自定义组件示例\nMyViewBinding");

    }

    public ViewBindingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewBindingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制黄色背景
        canvas.drawColor(Color.YELLOW);

    }
}
