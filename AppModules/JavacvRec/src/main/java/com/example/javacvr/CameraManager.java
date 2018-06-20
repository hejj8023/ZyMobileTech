package com.example.javacvr;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;

public class CameraManager {
	private Camera camera = null;
	private Size defaultSize = null;
	private boolean isPreviewOn = false;
	private int cameraFacingType = CameraInfo.CAMERA_FACING_BACK;
	private int frameRate = 30;

	public void startCamera() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			for (int i = 0; i < camera.getNumberOfCameras(); i++) {
				CameraInfo info = new CameraInfo();
				Camera.getCameraInfo(i, info);
				if (info.facing == cameraFacingType) {
					camera = Camera.open(i);
					break;
				}
			}
		} else {
			camera = Camera.open();
		}
		List<Size> sizeList = camera.getParameters().getSupportedPreviewSizes();
		int widthHeigh = 0;
		for (Size s : sizeList) {
			if (s.width * s.height > widthHeigh) {
				widthHeigh = s.width * s.height;
				defaultSize = s;
			}
		}
		camera.setDisplayOrientation(90);
	}

	public void setPreviewDisplay(SurfaceHolder holder) {
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPreviewCallBack(PreviewCallback callback) {
		camera.setPreviewCallback(callback);
	}

	public void startPreview() {
		if (!isPreviewOn && camera != null) {
			isPreviewOn = true;
			camera.startPreview();
		}
	}

	public void stopPreview() {
		if (isPreviewOn && camera != null) {
			isPreviewOn = false;
			camera.stopPreview();
		}
	} 

	public Size getDefaultSize() {
		return defaultSize;
	}

	public void closeCamera() {
		camera.stopPreview();
		camera.release();
		camera = null;
	}

	public boolean isUseBackCamera() {
		return cameraFacingType == CameraInfo.CAMERA_FACING_BACK;
	}

	public void useBackCamera() {
		cameraFacingType = CameraInfo.CAMERA_FACING_BACK;
	}

	public void useFrontCamera() {
		cameraFacingType = CameraInfo.CAMERA_FACING_FRONT;
	}

	public void changeCamera(SurfaceHolder holder) {
		if (cameraFacingType == CameraInfo.CAMERA_FACING_BACK) {
			useFrontCamera();
		} else if (cameraFacingType == CameraInfo.CAMERA_FACING_FRONT) {
			useBackCamera();
		}
		closeCamera();
		startCamera();
	}

	public void updateParameters() {
		Parameters camParams = camera.getParameters();
		camParams.setPreviewSize(defaultSize.width, defaultSize.height);

		Log.v("Camera", "Preview Framerate: " + camParams.getPreviewFrameRate());

		camParams.setPreviewFrameRate(frameRate);
		camera.setParameters(camParams);
	}

	public Camera getCamera() {
		return camera;
	}

}
