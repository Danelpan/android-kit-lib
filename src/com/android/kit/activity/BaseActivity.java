package com.android.kit.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.kit.common.CAsyncTask;
import com.android.kit.common.IAsyncTask;
import com.android.kit.common.ITaskListener;

public class BaseActivity extends FragmentActivity {

	private IAsyncTask mAsyncTask;

	public boolean isDestroy = false;
	public boolean isPause = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAsyncTask = new IAsyncTask();
	}

	@Override
	protected void onResume() {
		super.onResume();
		isDestroy = false;
		isPause = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isDestroy = true;
		mAsyncTask.destoryAyncTasks();
	}

	@Override
	protected void onPause() {
		isPause = true;
		super.onPause();
	}

	public CAsyncTask runAsyncTask(final ITaskListener taskListener, final int taskTag) {
		return mAsyncTask.runAsyncTask(taskListener, taskTag);
	}
}