package com.example.a6july_complexpermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mbtnPermission;
    private int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtnPermission = findViewById(R.id.button);
        mbtnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(MainActivity.this, permission, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            showToast("Both permission Granted");
        } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[1])) {
                showToast("Camera granted but Storage denied");
            } else {
                showToast("Camera granted but storage denied by selecting do not show again.");
            }
        } else if (grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                showToast("Storage granted but camera denied.");
            } else {
                showToast("Storage granted but camera denied by selecting do not show again.");
            }
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[1]) && ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                showToast("Both permission denied");
            } else {
                showToast("Both permission denied by selecting do not show again.");
            }
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}