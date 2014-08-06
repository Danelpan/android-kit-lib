package com.android.kit.common;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;

public class IAsyncTask {
	@SuppressLint("UseSparseArrays")
	private Map<Integer, CAsyncTask> tasks = Collections.synchronizedMap(new HashMap<Integer, CAsyncTask>());
	
	public IAsyncTask() {
	}

	@SuppressWarnings("unchecked")
	public CAsyncTask runAsyncTask(ITaskListener taskListener, int taskTag) {
		CAsyncTask asyncTask = new CAsyncTask(this,taskTag);
		asyncTask.setTaskListener(taskListener);
		insertTask(asyncTask, taskTag);
		asyncTask.execute(null,null);
		
		return asyncTask;
	}

	public void removeTask(int key) {
		CAsyncTask task = getAsyncTask(key);
		if (null != task) {
			task.cancel(true);
			tasks.remove(key);
		}
	}

	public void insertTask(CAsyncTask task, int key) {
		if (getAsyncTask(key) == null) {
			tasks.put(key, task);
		}
	}

	public CAsyncTask getAsyncTask(int key) {
		CAsyncTask task = tasks.get(key);
		return task;
	}
	
	public void destoryAyncTasks(){
		Collection<Integer> keys = tasks.keySet();
		for (Integer key : keys) {
			removeTask(key);
		}
		tasks.clear();
	}
	
	public void setcancel(int key , boolean cancel){
		CAsyncTask task = tasks.get(key);
		if(null != task){
			task.cancel(cancel);
		}
	}
}
