package com.example.chakrilagbe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.example.chakrilagbe.model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AllJobActivity extends AppCompatActivity {
    private Toolbar toolbar;

    //Recycler-
    private RecyclerView recyclerView;

    //Firebase-
    private DatabaseReference mAllJobPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_job);

        //toolbar=findViewById(R.id.all_job_post);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Job Post");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Database-
        mAllJobPost=FirebaseDatabase.getInstance().getReference().child("Public database");
        mAllJobPost.keepSynced(true);
        recyclerView=findViewById(R.id.recycler_all_job);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Data,AllJobPostViewHolder>adapter=new FirebaseRecyclerAdapter<Data, AllJobPostViewHolder>
                (
                        Data.class,
                        R.layout.alljobpost,
                        AllJobPostViewHolder.class,
                        mAllJobPost
                ) {
            @Override
            protected void populateViewHolder(AllJobPostViewHolder viewHolder, Data model, int i) {
                if(model.getIsApproved()!=0) {
                    viewHolder.setJobTitle(model.getTitle());
                    viewHolder.setJobDate(model.getDate());
                    viewHolder.setJobDescription(model.getDescription());
                    viewHolder.setJobSkills(model.getSkills());
                    viewHolder.setJobSalary(model.getSalary());
                }else {
                    viewHolder.myview.setVisibility(View.GONE);
                }

                viewHolder.myview.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public  void onClick(View view){
                        Intent intent=new Intent(getApplicationContext(),JobDetailsActivity.class);
                        intent.putExtra("title", model.getTitle());
                        intent.putExtra("date", model.getDate());
                        intent.putExtra("description", model.getDescription());
                        intent.putExtra("skills", model.getSkills());
                        intent.putExtra("salary", model.getSalary());

                        startActivity(intent);
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public static class AllJobPostViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public AllJobPostViewHolder(View itemView) {
            super(itemView);
            myview=itemView;
        }
        public void setJobTitle(String title){
            TextView mTitle=myview.findViewById(R.id.all_job_post_title);
            mTitle.setText(title);
        }
        public void setJobDate(String date){
            TextView mDate=myview.findViewById(R.id.all_job_post_date);
            mDate.setText(date);
        }

        public void setJobDescription(String description){
            TextView mDescription=myview.findViewById(R.id.all_job_post_description);
            mDescription.setText(description);
        }
        public void setJobSkills(String skills){
            TextView mSkills=myview.findViewById(R.id.all_job_post_skills);
            mSkills.setText(skills);
        }
        public void setJobSalary(String salary){
            TextView mSalary=myview.findViewById(R.id.all_job_post_salary);
            mSalary.setText(salary);
        }
    }

}