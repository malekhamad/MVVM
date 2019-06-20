package com.example.livedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.livedata.databinding.ActivityMainBinding;
import com.example.livedata.databinding.RecyclerViewItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDeleteListenerClick {
TextView textTitle , textDescription ;
RecyclerView recyclerView ;
RecyclerViewAdapter adapter;
    MainActivityViewModel viewModel;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this method for intialize views . . .. ;
        intializeViews();
     //   recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

         viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        // recyclerView.setAdapter(adapter);
        binding.recyclerView.setAdapter(adapter);

         viewModel.getSubjectList().observe(this, new Observer<List<Subject>>() {
             @Override
             public void onChanged(List<Subject> list) {
                 if(list.size() == 0){
                     viewModel.insertData(new Subject("malek","Mobile Developer"));
                     viewModel.insertData(new Subject("mohammed","Doctor"));
                     viewModel.insertData(new Subject("moatasem","ios Developer"));
                     viewModel.insertData(new Subject("isam","Engineerer"));

                 }
                 adapter.getAllSubject(list);
             }
         });

    }




    private void intializeViews(){
       // textTitle = findViewById(R.id.text_title);
        //textDescription = findViewById(R.id.text_description);
        //recyclerView = findViewById(R.id.recycler_view);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        adapter=new RecyclerViewAdapter(this);
    }

    public void DataBtn(View view) {


    }

    @Override
    public void onDeleteListenerClick(Subject subject) {
        viewModel.deleteSubject(subject);
        Toast.makeText(this, "delete Sucessfull", Toast.LENGTH_SHORT).show();
    }


    // create view holder for recycler view . . . ;
    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
       TextView texttitle , textdescription  ;
       ImageView imageEdit , imageDelete ;
       RecyclerViewItemBinding itemBinding ;
        public RecyclerViewHolder(RecyclerViewItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
         //   texttitle = itemView.findViewById(R.id.text_title);
           // textdescription = itemView.findViewById(R.id.text_description);

            imageEdit = itemView.findViewById(R.id.editImage);
            imageDelete = itemView.findViewById(R.id.deleteImage);
        }
        // this method to bind data inside views . . . ;
        public void bindView(Subject information){
          //  texttitle.setText(information.getTitle());
            //textdescription.setText(information.getDescription());

        }
        // this method to listen for click . . . ;
        public void setListener(final Subject subject) {


            // handle click on image Edit . . . ;
            imageEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("OnClick","imageEdit Clilcked "+subject.getId());
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    EditFragmentDialog fragmentDialog =EditFragmentDialog.getInstance(subject.getId());
                    fragmentDialog.show(fragmentManager,"editDialog");

                }
            });



        }
    }

    // create adapter for recycler view . . . ;
    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
      private   List<Subject> list=new ArrayList<>();
      private OnDeleteListenerClick onDeleteListenerClick ;

      public RecyclerViewAdapter(OnDeleteListenerClick listener){
          onDeleteListenerClick = listener ;
      }


        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           // View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view_item,parent,false);
            RecyclerViewItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.recycler_view_item,parent,false);

            return new RecyclerViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            final Subject information = list.get(position);
            //   holder.bindView(information);
            holder.itemBinding.setSubject(information);

               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       viewModel.insertData(new Subject(information.getTitle(),information.getDescription()));
                       Toast.makeText(MainActivity.this, "subject saved", Toast.LENGTH_SHORT).show();
                   }
               });

               holder.setListener(information);

               // set handle for delete button (when user click to trash button ) . . . . . ;
               holder.imageDelete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       onDeleteListenerClick.onDeleteListenerClick(information);
                   }
               });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void getAllSubject(List<Subject>list){
            this.list=list;
            this.notifyDataSetChanged();
        }
    }






}
