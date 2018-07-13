package developer.code.kpchandora.calcdemo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyHolder> {

    private ArrayList<ResultsModel> modelArrayList;
    private Context context;

    public ResultAdapter(ArrayList<ResultsModel> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        ResultsModel model = modelArrayList.get(position);
        holder.mainResult.setText("= " + model.getMainResult());
        holder.operationResult.setText(model.getOperations());

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView mainResult, operationResult;

        public MyHolder(View itemView) {
            super(itemView);
            mainResult = itemView.findViewById(R.id.result_textView);
            operationResult = itemView.findViewById(R.id.operation_textView);

        }
    }
}
