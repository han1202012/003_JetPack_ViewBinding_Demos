package kim.hsl.vb;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import kim.hsl.vb.databinding.DialogBinding;

public class ViewBindingDialog extends Dialog {

    public ViewBindingDialog(@NonNull Context context) {
        super(context);
    }

    public ViewBindingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ViewBindingDialog(@NonNull Context context, boolean cancelable,
                                @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1 . 获取视图绑定类
        DialogBinding binding = DialogBinding.inflate(getLayoutInflater());

        // 2 . 设置对话框布局
        setContentView(binding.getRoot());

        // 3 . 通过视图绑定类访问布局中的视图组件
        binding.textView.setText("视图绑定对话框示例 \nDialogBinding");

        // 4 . 设置对话框大小 ( 仅做参考 美观处理 与主题无关 )
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = 400;
        getWindow().setAttributes(params);

    }

}
