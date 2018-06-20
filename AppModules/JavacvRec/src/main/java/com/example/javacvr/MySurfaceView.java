package com.example.javacvr;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * class extends SurfaceView, main purpose is to refresh the preview
 * 
 * @author xiaodong
 * 
 */
public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback, PreviewCallback {

	private CameraManager cameraManager = null;
	private RecorderManager recorderManager = null;
	private SurfaceHolder mHolder;

	public MySurfaceView(Context paramContext) {
		super(paramContext);

//		init(paramContext);

	}

	public MySurfaceView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
//		init(paramContext);
	}

	public void init(Context paramContext) {
		cameraManager = ((MainActivity) paramContext).getCameraManager();
		recorderManager = ((MainActivity) paramContext).getRecorderManager();
		cameraManager.startCamera();

		mHolder = getHolder();
		mHolder.addCallback(MySurfaceView.this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		cameraManager.setPreviewCallBack(MySurfaceView.this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		Log.e("SurfaceView", "surfaceChanged!");
		cameraManager.updateParameters();
		cameraManager.startPreview();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		mHolder.addCallback(MySurfaceView.this);
		cameraManager.setPreviewDisplay(holder);
		recorderManager.initRecorder();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		cameraManager.stopPreview();
		mHolder.addCallback(null);
		cameraManager.setPreviewDisplay(null);

	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		Log.e(RecorderManager.LOG_TAG, "MySurfaceView onPreviewFrame");
		recorderManager.onPreviewFrame(data, camera);
	}
}
