package com.fpt.bbusbe.service;


import com.fpt.bbusbe.model.entity.Camera;
import com.fpt.bbusbe.model.entity.key.CameraKey;

public interface CameraService {
    Camera createOrUpdateCamera(Camera camera);
}
