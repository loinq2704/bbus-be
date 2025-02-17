package com.fpt.bbusbe.controller;

import com.fpt.bbusbe.model.entity.Camera;
import com.fpt.bbusbe.service.CameraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camera")
@Tag(name = "Camera Controller")
@Slf4j(topic = "CAMERA-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @GetMapping
    public Page<Camera> getAllProducts(Pageable pageable) {
        return cameraService.findAll(pageable);
    }
}
