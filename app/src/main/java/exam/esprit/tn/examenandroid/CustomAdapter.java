package exam.esprit.tn.examenandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Film> implements View.OnClickListener {
    Context context;
    List<Film> todoList;
    LayoutInflater inflter;
    Film todo;
    View v;

    public CustomAdapter(List<Film> data, Context context) {
        super(context, R.layout.todo_list_item, data);
        this.todoList = data;
        this.context = context;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Film getItem(int i) {
        return todoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflter.inflate(R.layout.todo_list_item, null);
        TextView ligne1 = view.findViewById(R.id.txtNom);
        ligne1.setText(todoList.get(i).getNom());
        //ligne2.setText(ligne2.getText().toString() + todoList.get(i).getBody());
        ImageView img = view.findViewById(R.id.imgIcone);
        if (!todoList.get(i).getTypeProjection().equals("Premiére projection"))
            img.setVisibility(View.INVISIBLE);
        v = view;
        notifyDataSetChanged();
        //imgDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
//                databaseHelper.deleteTodo(ListTodoFragment.dataModel);
//                todoList= databaseHelper.getAllTodos();
//                notifyDataSetChanged();
//            }
//        });
        return view;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        todo = getItem(position);
        Log.d("erreur", todo.getNom());
    }
}