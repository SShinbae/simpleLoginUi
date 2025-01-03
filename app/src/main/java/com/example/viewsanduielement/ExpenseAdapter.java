package com.example.viewsanduielement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private List<Expense> expenses;

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.bind(expense);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        notifyItemInserted(expenses.size() - 1);
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private TextView expName, expDate, expValue, expQty, expTotal;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            expName = itemView.findViewById(R.id.expName);
            expDate = itemView.findViewById(R.id.expDate);
            expValue = itemView.findViewById(R.id.expValue);
            expQty = itemView.findViewById(R.id.expQty);
            expTotal = itemView.findViewById(R.id.expTotal);
        }

        public void bind(Expense expense) {
            expName.setText(expense.getStrExpName());
            expDate.setText(expense.getStrExpDate());
            expValue.setText(String.valueOf(expense.getStrExpValue()));
            expQty.setText(String.valueOf(expense.getStrExpQty()));
            expTotal.setText(String.valueOf(expense.getStrExpTotal()));
        }
    }
}