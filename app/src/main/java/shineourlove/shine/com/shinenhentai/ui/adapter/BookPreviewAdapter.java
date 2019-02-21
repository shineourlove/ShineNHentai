package shineourlove.shine.com.shinenhentai.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import shineourlove.shine.com.shinenhentai.R;
import shineourlove.shine.com.shinenhentai.api.BookModel;

/**
 * created by shineourlove on 2019/2/21
 */
public class BookPreviewAdapter extends RecyclerView.Adapter<BookPreviewAdapter.BookHolder> {
    private Context context;
    private List<BookModel> bookList;

    public BookPreviewAdapter(Context context, List<BookModel> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BookHolder(LayoutInflater.from(context).inflate(R.layout.adapter_book_preview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookHolder extends RecyclerView.ViewHolder {
        BookHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
