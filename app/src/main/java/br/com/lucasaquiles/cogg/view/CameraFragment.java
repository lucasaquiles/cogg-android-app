package br.com.lucasaquiles.cogg.view;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

import br.com.lucasaquiles.cogg.TakePictureActivity;
import br.com.lucasaquiles.cogg.utils.CameraFragmentListener;
import br.com.lucasaquiles.cogg.utils.CameraPreview;
import br.com.lucasaquiles.cogg.utils.PhotoHandler;


public class CameraFragment extends Fragment implements SurfaceHolder.Callback, View.OnClickListener {
    public static final String TAG = "Mustache/CameraFragment";

    private static final int PICTURE_SIZE_MAX_WIDTH = 1280;
    private static final int PREVIEW_SIZE_MAX_WIDTH = 640;

    private int cameraId;
    private Camera camera;
    private SurfaceHolder surfaceHolder;
    private CameraFragmentListener listener;
    private Button button;

    /**
     * On activity getting attached.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof CameraFragmentListener)) {
            throw new IllegalArgumentException(
                    "Activity has to implement CameraFragmentListener interface"
            );
        }

        listener = (CameraFragmentListener) activity;



    }

    /**
     * On creating view for fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


//        CameraPreview previewView = (CameraPreview) view.findViewById(R.id.cameraPreview);

        CameraPreview previewView = new CameraPreview(getActivity());


        previewView.getHolder().addCallback(this);

        return previewView;
    }

    /**
     * On fragment getting resumed.
     */
    @Override
    public void onResume() {
        super.onResume();


        button = ((TakePictureActivity) getActivity()).getButton();
        button.setOnClickListener(this);

        try {
            camera = Camera.open(cameraId);

        } catch (Exception exception) {
            Log.e(TAG, "Can't open camera with id " + cameraId, exception);

            listener.onCameraError();
            return;
        }
    }

    /**
     * On fragment getting paused.
     */
    @Override
    public void onPause() {
        super.onPause();

        stopCameraPreview();
        camera.release();
    }

    /**
     * Start the camera preview.
     */
    private synchronized void startCameraPreview() {
        determineDisplayOrientation();
        setupCamera();

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException exception) {
            Log.e(TAG, "Can't start camera preview due to IOException", exception);

            listener.onCameraError();
        }
    }

    /**
     * Stop the camera preview.
     */
    private synchronized void stopCameraPreview() {
        try {
            camera.stopPreview();
        } catch (Exception exception) {
            Log.i(TAG, "Exception during stopping camera preview");
        }
    }

    /**
     * Determine the current display orientation and rotate the camera preview
     * accordingly.
     */
    public void determineDisplayOrientation() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, cameraInfo);

        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        int degrees  = 0;

        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;

            case Surface.ROTATION_90:
                degrees = 90;
                break;

            case Surface.ROTATION_180:
                degrees = 180;
                break;

            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int displayOrientation;

        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            displayOrientation = (cameraInfo.orientation + degrees) % 360;
            displayOrientation = (360 - displayOrientation) % 360;
        } else {
            displayOrientation = (cameraInfo.orientation - degrees + 360) % 360;
        }

        camera.setDisplayOrientation(displayOrientation);
    }

    /**
     * Setup the camera parameters.
     */
    public void setupCamera() {
        Camera.Parameters parameters = camera.getParameters();

        Camera.Size bestPreviewSize = determineBestPreviewSize(parameters);
       Camera.Size bestPictureSize = determineBestPictureSize(parameters);

        parameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
        parameters.setPictureSize(bestPictureSize.width, bestPictureSize.height);

        camera.setParameters(parameters);
    }

    private Camera.Size determineBestPreviewSize(Camera.Parameters parameters) {
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();

        return determineBestSize(sizes, PREVIEW_SIZE_MAX_WIDTH);
    }

    private Camera.Size determineBestPictureSize(Camera.Parameters parameters) {
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();

        return determineBestSize(sizes, PICTURE_SIZE_MAX_WIDTH);
    }

    protected Camera.Size determineBestSize(List<Camera.Size> sizes, int widthThreshold) {
        Camera.Size bestSize = null;

        for (Camera.Size currentSize : sizes) {
            boolean isDesiredRatio = (currentSize.width / 4) == (currentSize.height / 3);
            boolean isBetterSize = (bestSize == null || currentSize.width > bestSize.width);
            boolean isInBounds = currentSize.width <= PICTURE_SIZE_MAX_WIDTH;

            if (isDesiredRatio && isInBounds && isBetterSize) {
                bestSize = currentSize;
            }
        }

        if (bestSize == null) {
            listener.onCameraError();

            return sizes.get(0);
        }

        return bestSize;
    }

    /**
     * On camera preview surface created.
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.surfaceHolder = holder;

        startCameraPreview();
    }

    /**
     * On camera preview surface changed.
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // The interface forces us to have this method but we don't need it
        // up to now.
    }

    /**
     * On camera preview surface getting destroyed.
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // We don't need to handle this case as the fragment takes care of
        // releasing the camera when needed.
    }



    @Override
    public void onClick(View v) {

        camera.takePicture(null, null, new PhotoHandler(getActivity()));

       // Toast.makeText(getActivity(), "eita", Toast.LENGTH_LONG).show();
    }
}
