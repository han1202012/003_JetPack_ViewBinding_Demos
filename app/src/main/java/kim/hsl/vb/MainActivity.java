package kim.hsl.vb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kim.hsl.vb.databinding.ActivityMainBinding;
import kim.hsl.vb.databinding.ItemBinding;

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

}
