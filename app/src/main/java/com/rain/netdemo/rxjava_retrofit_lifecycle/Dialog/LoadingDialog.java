package com.rain.netdemo.rxjava_retrofit_lifecycle.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.rain.netdemo.R;


public class LoadingDialog extends Dialog {

	public LoadingDialog(@NonNull Context context) {
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_commom_loading);
	}

}

