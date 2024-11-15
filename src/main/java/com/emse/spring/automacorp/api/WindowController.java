package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.dto.WindowMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {
    private final WindowDao windowDao;
    private final RoomDao roomDao;
    private final SensorDao sensorDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao, SensorDao sensorDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.sensorDao = sensorDao;
    }



// Send window List
    @GetMapping
    public List<Window> findAll() {
        return windowDao.findAll()
                .stream()
                .map(WindowMapper::of)
                .sorted(Comparator.comparing(Window::name))
                .collect(Collectors.toList());
    }

    // Add a window
    @PostMapping
    public ResponseEntity<Window> create(@RequestBody WindowCommand window) {
        RoomEntity room = roomDao.findById(window.roomId()).orElse(null);

        if (room==null) {
            return ResponseEntity.badRequest().build();
        }

        SensorEntity windowStatus = new SensorEntity(SensorType.STATUS, window.name() + " status");
        windowStatus.setValue(window.windowStatus());
        sensorDao.save(windowStatus);

        WindowEntity entity = new WindowEntity(window.name(), windowStatus, room);
        WindowEntity saved = windowDao.save(entity);
        return ResponseEntity.ok(WindowMapper.of(saved));
    }



    // Update a window status
    @PutMapping(path = "/{id}")
    public ResponseEntity<Window> update(@PathVariable Long id, @RequestBody WindowCommand window) {
        WindowEntity entity = windowDao.findById(id).orElse(null);
        RoomEntity room = roomDao.findById(window.roomId()).orElse(null);

        if (entity == null || room==null) {
            return ResponseEntity.badRequest().build();
        }

        SensorEntity windowStatus = new SensorEntity(SensorType.STATUS, window.name() + " status");
        windowStatus.setValue(window.windowStatus());
        sensorDao.save(windowStatus);

        entity.setName(window.name());
        entity.setWindowStatus(windowStatus);
        entity.setRoom(room);
        return ResponseEntity.ok(WindowMapper.of(entity));
    }

    // Read a Window
    @GetMapping(path = "/{id}")
    public Window findById(@PathVariable Long id) {
        return windowDao.findById(id).map(WindowMapper::of).orElse(null);
    }

    // Delete a window
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }






}
