# 【JetPack】视图绑定 ( ViewBinding ) 各种应用 ( 视图绑定两种方式 | Activity 布局 | 对话框布局 | 自定义组件布局 | RecyclerView 列表布局 )

<br>
<br>

https://hanshuliang.blog.csdn.net/article/details/105066654




<br>
<br>

#### I . 视图绑定 ( ViewBinding ) 界面的两种方式

---

<br>

**1 . ViewBinding 视图绑定类提供了两种与界面的绑定方式 , 分别是** 

**① <font color=red>XxxBinding.inflate( LayoutInflater )** 

**② <font color=magenta>XxxBinding.inflate( LayoutInflater , ViewParent, attachToRoot )** 

**两种方式 ; <font color=green> ( XxxBinding 是视图绑定类 )**


<br>

**2 . XxxBinding.inflate( LayoutInflater ) 与界面绑定 :** <font color=red>这种方式加载的布局与界面关联性不大 , 需要调用额外的函数 , 将视图绑定类与界面进行绑定 , Activity 界面 与 Dialog 对话框 , 就使用这种绑定方式 ; 

<br>

**3 . XxxBinding.inflate( LayoutInflater , ViewParent, attachToRoot ) 直接与界面绑定 :** <font color=magenta>自定义布局组件 和 RecyclerView 适配器中为条目加载布局选项 , 就是使用的这种方式 , 调用该方法后 , 可以直接与界面进行绑定 , 界面中显示的就是 XxxBinding 对应的布局内容 ; 

<br>
<br>

#### II . Activity 界面中 应用 视图绑定 ( ViewBinding ) 

---

<br>

**Activity 界面中 应用 视图绑定 ( ViewBinding )  :** 

<br>

**① 获取视图绑定类 :** <font color=blue>使用 ActivityMainBinding.inflate(getLayoutInflater()) 只是单纯的加载布局 ; 

**② 关联界面 :** <font color=red>还需要调用 setContentView(binding.getRoot()) 方法 , 将 视图绑定类与 Activity 界面关联 , 此时才能通过视图绑定类获取组件 , 进而控制 UI 界面 ; 


<br>

```java
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

        // 4 . 视同视图绑定类获取按钮 , 并未按钮设置点击事件
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewBindingDialog dialog = new ViewBindingDialog(MainActivity.this);
                dialog.show();
            }
        });


        // 5 . 设置 Recycler View

        // 5.1  为 RecyclerView 列表设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        // 5.2 为 RecyclerView 列表设置适配器
        binding.recyclerView.setAdapter(new Adapter());
    }
}
```


<br>
<br>

#### III . Dialog 对话框界面中 应用 视图绑定 ( ViewBinding ) 

---

<br>

**Dialog 对话框界面中 应用 视图绑定 ( ViewBinding )   :** 该界面与 Activity 界面用法基本相同 ; 

<br>

**① 获取视图绑定类 :** <font color=blue>使用 DialogBinding binding = DialogBinding.inflate(getLayoutInflater()) 只是单纯的加载布局 ; 

**② 关联界面 :** <font color=red>还需要调用 setContentView(binding.getRoot()) 方法 , 将 视图绑定类与 Dialog 对话框界面关联 , 此时才能通过视图绑定类获取组件 , 进而控制 UI 界面 ; 

```java
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
```

<br>

**效果展示 :** 

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200325001533840.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhbjEyMDIwMTI=,size_16,color_FFFFFF,t_70)



<br>
<br>

#### IV . 自定义组件 应用 视图绑定 ( ViewBinding ) 

---

<br>

**自定义组件 应用 视图绑定 ( ViewBinding ) :** 

<br>

**① 自定义组件首先是 ViewGroup 子类 , View 子类无法使用视图绑定 ;** 

**② 初始化视图绑定类并关联界面 :** 使用 `MyViewBinding binding = MyViewBinding.inflate(inflater, this, true)` 进行视图绑定初始化 , 及 关联界面操作 , 其中的 this 就是 ViewGroup 类型的 , 即组件本身 , 调用上述方法 , 可以将两个操作都完成 ; 


<br>



```java
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
```


<br>
<br>

#### V . RecyclerView 列表布局 应用 视图绑定 ( ViewBinding ) 

---

<br>

**RecyclerView 列表布局 应用 视图绑定 ( ViewBinding )  :** 

<br>

**① 视图绑定需要在 Adapter 适配器的 onCreateViewHolder( ) 方法中进行初始化 , 只有在这里才能拿到 ViewGroup parent 关联组件的父类容器 ;** 

**② 初始化视图绑定类并关联界面 :** 使用 `ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(MainActivity.this) , parent, false)` 进行视图绑定初始化 , 及 关联界面操作 , 其中的 this 就是 ViewGroup 类型的 , 即组件本身 , 调用上述方法 , 可以将两个操作都完成 ; 

**③ 自定义 ViewHolder 构造函数 :** `public ViewHolder(ItemBinding binding)` 传入视图绑定类 , 在构造函数中使用视图绑定类初始化 ViewHolder 中的组件 , 注意别忘了先调用父类的方法 ; 

<br>

```cpp
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(MainActivity.this) , parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText("第 " + position + " 行");
            if(position % 2 == 0){
                holder.textView.setBackgroundColor(Color.YELLOW);
            }else{
                holder.textView.setBackgroundColor(Color.WHITE);
            }
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView textView;

            /**
             * 自定义的构造函数 , 需要传入 视图绑定类
             * @param binding
             *            列表项布局的视图绑定类
             */
            public ViewHolder(ItemBinding binding) {
                super(binding.getRoot());
                this.textView = binding.textView;
            }
        }
    }

```

<br>

**效果展示 :** 

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200325001452382.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhbjEyMDIwMTI=,size_16,color_FFFFFF,t_70)





<br>
<br>

#### VI . GitHub 代码地址

---

<br>


**GitHub 代码地址 : [https://github.com/han1202012/001_JetPack_ViewBinding](https://github.com/han1202012/001_JetPack_ViewBinding)**





