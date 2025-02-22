package com.fpt.bbusbe.service;


import com.fpt.bbusbe.model.entity.Camera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CameraService {
    Camera createOrUpdateCamera(Camera camera);

    Page<Camera> findAll(Pageable pageable);

    Camera findOne(String id);
}
