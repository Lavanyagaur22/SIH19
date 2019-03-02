package com.codingblocks.sih19;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ImmunizationFAQDetailList  extends RecyclerView.Adapter<ImmunizationFAQDetailList.MyHolder> {

    List<String> questions, answers;

    public ImmunizationFAQDetailList(List<String> questions, List<String> answers) {

        this.questions = questions;
        this.answers = answers;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faq_row, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        holder.questionTextView.setText(questions.get(position));

        holder.answerTextView.setText(answers.get(position));

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public TextView questionTextView, answerTextView;

        public MyHolder(View itemView) {
            super(itemView);

            questionTextView = itemView.findViewById(R.id.questionTextView);
            answerTextView = itemView.findViewById(R.id.answerTextView);
        }
    }
}
