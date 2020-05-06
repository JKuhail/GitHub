package com.jkuhail.github.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jkuhail.github.R;
import com.jkuhail.github.app.Util;
import com.jkuhail.github.models.Repository;

import java.util.List;

import io.realm.Realm;

/** The Adapter Pattern in android is pre-defined!
  * The Adapter pattern convert the interface of a class into another interface clients expect.
 * so as the android Adapter do. Here we takes the data from GitHub API and from the local database and puts it into a View
 * which is the UI here.*/

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder> {

	private static final String TAG = DisplayAdapter.class.getSimpleName();

	private List<Repository> mData;
	private LayoutInflater inflater;
	private Context mContext;

	public DisplayAdapter(Context context, List<Repository> items) {
		inflater = LayoutInflater.from(context);
		this.mData = items;
		this.mContext = context;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_item, parent, false);
		MyViewHolder holder = new MyViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		Repository current = mData.get(position);
		holder.setData(current, position);
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	public void swap(List<Repository> data)
	{
		if (data.size() == 0)
			Util.showMessage(mContext, "No Items Found");
		mData = data;
		notifyDataSetChanged();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		private TextView name, language, stars, watchers, forks;
		private int position;
		private ImageView imgBookmark;
		private Repository current;

		public MyViewHolder(View itemView) {
			super(itemView);

			name = itemView.findViewById(R.id.txvName);
			language = itemView.findViewById(R.id.txvLanguage);
			stars = itemView.findViewById(R.id.txvStars);
			watchers = itemView.findViewById(R.id.txvWatchers);
			forks = itemView.findViewById(R.id.txvForks);

			imgBookmark = itemView.findViewById(R.id.img_bookmark);
			imgBookmark.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					bookmarkRepository(current);
				}
			});

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//[Facade] #4: call the method to a particular object
					current.print(mContext , current.getName());
					String url = current.getHtmlUrl();
					Uri webPage = Uri.parse(url);
					Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
					if (intent.resolveActivity(mContext.getPackageManager()) != null) {
						mContext.startActivity(intent);
					}
				}
			});
		}

		public void setData(Repository current, int position) {

			this.name.setText(current.getName());
			this.language.setText(String.valueOf(current.getLanguage()));
			this.forks.setText(String.valueOf(current.getForks()));
			this.watchers.setText(String.valueOf(current.getWatchers()));
			this.stars.setText(String.valueOf(current.getStars()));
			this.position = position;
			this.current = current;
		}

		private void bookmarkRepository(final Repository current) {
			Realm.init(mContext);
			Realm realm = Realm.getDefaultInstance();
			realm.executeTransactionAsync(new Realm.Transaction() {
				@Override
				public void execute(@NonNull Realm realm) {
					realm.copyToRealmOrUpdate(current);
				}
			}, new Realm.Transaction.OnSuccess() {
				@Override
				public void onSuccess() {
					Util.showMessage(mContext, "Bookmarked Successfully");
				}
			}, new Realm.Transaction.OnError() {
				@Override
				public void onError(Throwable error) {
					Log.i(TAG, error.toString());
					Util.showMessage(mContext, "Error Occurred");
				}
			});
		}
	}
}
