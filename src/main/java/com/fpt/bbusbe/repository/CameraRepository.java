package com.fpt.bbusbe.repository;

import com.fpt.bbusbe.model.entity.Camera;
import com.fpt.bbusbe.model.entity.key.CameraKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, CameraKey> {

}
