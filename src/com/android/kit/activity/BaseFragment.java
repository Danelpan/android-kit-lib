package com.android.kit.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.kit.common.CAsyncTask;
import com.android.kit.common.IAsyncTask;
import com.android.kit.common.ITaskListener;

public class BaseFragment extends Fragment {
	private IAsyncTask mAsyncTask;	
	public boolean isDestroy = false;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAsyncTask = new IAsyncTask();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isDestroy = true;
		mAsyncTask.destoryAyncTasks();
	}

	@Override
	public void onResume() {
		super.onResume();
		isDestroy = false;
	}
	
	public CAsyncTask runAsyncTask(ITaskListener taskListener, int taskTag) {
		return mAsyncTask.runAsyncTask(taskListener, taskTag);
	}
}
