package com.ppob.client.android;

import java.util.List;

import com.ppob.client.android.domain.Product;
import com.ppob.client.android.service.ServerConnection;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class RequestProductListActivity extends ListActivity{
	private static String TAG = RequestProductListActivity.class.getSimpleName();
	private ProgressDialog progressDialog;
	private boolean destroyed = false;
	private String a, p;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Bundle bundle = this.getIntent().getExtras();
		a = bundle.getString("serverAddress");
		p = bundle.getString("serverPort");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.destroyed = true;
	}
    
    public void showLoadingProgressDialog() {
		this.showProgressDialog("Loading. Please wait...");
	}

	public void showProgressDialog(CharSequence message) {
		if (this.progressDialog == null) {
			this.progressDialog = new ProgressDialog(this);
			this.progressDialog.setIndeterminate(true);
		}

		this.progressDialog.setMessage(message);
		this.progressDialog.show();
	}

	public void dismissProgressDialog() {
		if (this.progressDialog != null && !this.destroyed) {
			this.progressDialog.dismiss();
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();

		new DownloadStatesTask(a, p).execute();
	}
	
	private void refreshStates(List<Product> productList) {
		if (productList == null) {
			return;
		}

		ProductListAdapter adapter = new ProductListAdapter(this, productList);
		setListAdapter(adapter);
	}
	
	private class DownloadStatesTask extends AsyncTask<Void, Void, List<Product>> {

		private String ipServer;
		private String portServer;
		
		public DownloadStatesTask(String ipServer, String portServer) {
			this.ipServer = ipServer;
			this.portServer = portServer;
		}

		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();
		}

		@Override
		protected List<Product> doInBackground(Void... params) {
			try {
				ServerConnection c = new ServerConnection(ipServer, portServer);
				return c.getProductData();
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<Product> result) {
			dismissProgressDialog();
			refreshStates(result);
		}

	}
}
