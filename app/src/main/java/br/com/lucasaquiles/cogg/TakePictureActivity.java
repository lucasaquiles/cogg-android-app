package br.com.lucasaquiles.cogg;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.lucasaquiles.cogg.utils.CameraFragmentListener;


public class TakePictureActivity extends FragmentActivity implements CameraFragmentListener, View.OnClickListener {


    private Button button;
    private Button buttonUseImage;
    private int cameraId = 0;

    public static final String TAG = "Mustache/CameraActivity";
    /**
     * On activity getting created.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_take_picture);

        button = (Button) findViewById(R.id.button1);
        buttonUseImage = (Button) findViewById(R.id.button2);
      //  button.setOnClickListener(this);
    }

    /**
     * On fragment notifying about a non-recoverable problem with the camera.
     */
    @Override
    public void onCameraError() {
        Toast.makeText(
                this,  "deu pau",  Toast.LENGTH_SHORT
        ).show();

        finish();
    }

    public Button getButton() {
        return button;
    }

    public Button getButtonUseImage() {
        return buttonUseImage;
    }

    @Override
    public void onClick(View v) {


//        Camera camera = null;
//
//        if (!getPackageManager()
//                .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
//            Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
//                    .show();
//        } else {
//            try {
//                cameraId = findFrontFacingCamera();
//                if (cameraId < 0) {
//                    Toast.makeText(this, "No front facing camera found.",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    camera = Camera.open(cameraId);
//                }
//            }catch(Exception e){
//             //   e.printStackTrace();
//            }
//        }
//
//        assert camera != null;
//        camera.takePicture(null, null, new PhotoHandler(getApplicationContext()));
    }
}
