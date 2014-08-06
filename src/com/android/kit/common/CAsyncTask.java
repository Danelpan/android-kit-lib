package com.android.kit.common;

import android.os.AsyncTask;
import android.os.Bundle;

import com.android.kit.utils.KitLog;

@SuppressWarnings("rawtypes")
public class CAsyncTask extends AsyncTask {
	private ITaskListener listener;
	private int mTaskTag = 0x0;
	private Bundle mBundle;

	private IAsyncTask mAsyncTask;
	
	public CAsyncTask(int tag){
		this(null, tag);
	}
	
	public CAsyncTask(IAsyncTask asyncTask, int tag) {
		this.mTaskTag = tag;
		this.mAsyncTask = asyncTask;
	}

	public void setTaskListener(ITaskListener listener) {
		this.listener = listener;
	}

	@Override
	protected Object doInBackground(Object... params) {
		
		return listener.onTaskLoading(mBundle, mTaskTag);
	}


	@Override
	protected void onPostExecute(Object result) {
		if (isCancelled()) {
			KitLog.e("CAsyncTask", String.format("%s task thread %d have canceled",this.toString(),mTaskTag));
		} else{
			listener.onTaskFinish(mBundle, mTaskTag, result);
		}
		if(null != mAsyncTask){
			mAsyncTask.removeTask(mTaskTag);
		}
	}

	@Override
	protected void onPreExecute() {
		mBundle = listener.onTaskStart(mTaskTag);
	}
	
}
