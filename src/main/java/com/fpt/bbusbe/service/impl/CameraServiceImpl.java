package com.fpt.bbusbe.service.impl;

import com.fpt.bbusbe.model.entity.Camera;
import com.fpt.bbusbe.model.entity.key.CameraKey;
import com.fpt.bbusbe.repository.CameraRepository;
import com.fpt.bbusbe.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public Camera createOrUpdateCamera(Camera camera) {
        // Use save to either create or update the record
        return cameraRepository.save(camera);
    }
}
