package com.dvp.android.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ObjetAdapter extends ArrayAdapter<Objects> {
	

	public ObjetAdapter(Context context, int textViewResourceId,
                        List<Objects> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View finalView = null;

		if (convertView != null) {
			finalView = convertView;

		} else {

			finalView = LayoutInflater.from(getContext()).inflate(
					R.layout.affichageitem, null);
		}

        Objects object = getItem(position);

		ViewHolder holder = (ViewHolder) finalView.getTag();
		if (holder == null) {
			holder = new ViewHolder();
			holder.nom = (TextView) finalView.findViewById(R.id.Champs1);
			holder.erre = (TextView) finalView.findViewById(R.id.Champs2);
			holder.image = (ImageView) finalView.findViewById(R.id.image);
		}

		holder.nom.setText(object.Nom);
		holder.erre.setText(object.Erre);
		holder.task = new LoadImageTask(holder);
		holder.task.execute(object.LienImage);
		
		return finalView;
	}

	private Bitmap downloadImage(String lienImage) {
		Bitmap bitmap = null;

		try {

			URL urlImage = new URL(lienImage);
			HttpURLConnection connection = (HttpURLConnection) urlImage
					.openConnection();

			InputStream inputStream = connection.getInputStream();

			return BitmapFactory.decodeStream(inputStream);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
		return null;
	}

	private class LoadImageTask extends AsyncTask<String, Void, Bitmap> {


		private ViewHolder holder;
		
		public LoadImageTask(ViewHolder holder) {
			this.holder = holder;
		}
		
		@Override
		protected Bitmap doInBackground(String... lienImages) {
            String lienImage = lienImages[0];
			return downloadImage(lienImage);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			if (holder.task == this) {
			    holder.image.setImageBitmap(result);
			}
		}
		
	}
	
	public class ViewHolder {
		public TextView nom;
		public TextView erre;
		public ImageView image;
		public LoadImageTask task;

	}

}
