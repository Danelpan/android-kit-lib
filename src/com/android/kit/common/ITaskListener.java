package com.android.kit.common;

import android.os.Bundle;

/**
 * 执行异步任务的接口，该接口回调是在线程中被回调的 <br>
 * 当任务完成之后，我们需要单个唤起跟新UI线程
 * @author Danel
 * 
 */
public interface ITaskListener {
	/**
	 * 任务开始的时候，该函数将会被回调，该函数的回调在UI线程中执行<br>
	 * 一般使用该函数时，是做初始化携带数据使用
	 */
	Bundle onTaskStart(int taskTag);

	/**
	 * 任务执行中，该函数将会被回调，函数回调在线程中执行<br>
	 * 在该函数中请不要做一些更新UI的操作
	 */
	Object onTaskLoading(Bundle bundle, int taskTag);

	/**
	 * 任务结束后，该函数被回调，该函数的回调是在UI线程中执行的,具体的返回值result是 <br>
	 * 当线程执行中被结束，那么该函数将不会再被回调
	 */
	void onTaskFinish(Bundle bundle, int taskTag, Object data);

}
