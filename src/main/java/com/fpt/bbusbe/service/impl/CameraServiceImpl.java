package com.fpt.bbusbe.service.impl;

import com.fpt.bbusbe.model.entity.Camera;
import com.fpt.bbusbe.repository.CameraRepository;
import com.fpt.bbusbe.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public Camera createOrUpdateCamera(Camera camera) {
        // Use save to either create or update the record
        return cameraRepository.save(camera);
    }

    @Override
    public Page<Camera> findAll(Pageable pageable) {
        return cameraRepository.findAll(pageable);
    }

    @Override
    public Camera findOne(String id) {
        Optional<Camera> optionalCamera = cameraRepository.findById(id);
        return optionalCamera.isPresent() ? optionalCamera.get() : null;
    }
}
