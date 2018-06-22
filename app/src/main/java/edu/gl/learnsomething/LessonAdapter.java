package edu.gl.learnsomething;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder> {

    private List<String> lessonArray;

    public List<String> getLessonArray() {
        return lessonArray;
    }

    public void setLessonArray(List<String> lessonArray) {
        this.lessonArray = lessonArray;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lessonNameTextView;

        public MyViewHolder(View view) {
            super(view);
            lessonNameTextView = (TextView) view.findViewById(R.id.lesson_name_textview);
        }
    }


    public LessonAdapter(List<String> moviesList) {
        this.setLessonArray(moviesList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lesson_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            String name = lessonArray.get(position);
            holder.lessonNameTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return getLessonArray().size();
    }


}