package com.ppob.client.android;

import java.util.List;

import com.ppob.client.android.domain.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProductListAdapter extends BaseAdapter{
	private List<Product> productList;
	private final LayoutInflater layoutInflater;
	
	public ProductListAdapter(Context context, List<Product> productList){
		this.productList = productList;
		this.layoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount(){
		return this.productList != null ? productList.size() : 0;
	}
	
	@Override
	public Product getItem(int position){
		return this.productList.get(position);		
	}
	
	@Override
	public long getItemId(int position){
		return position;
	}
	
	@Override 
	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			convertView = this.layoutInflater.inflate(R.layout.product_list, parent, false);
		}
		
		Product product = getItem(position);
		if(product != null){
			TextView txtCode = (TextView) convertView.findViewById(R.id.product_code);
			txtCode.setText(product.getCode());
			TextView txtName = (TextView) convertView.findViewById(R.id.product_name);
			txtName.setText(product.getName());
		}
		return convertView;
	}
}
