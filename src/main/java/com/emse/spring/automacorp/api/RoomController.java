package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.RoomMapper;
import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.SensorMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController //
@RequestMapping("/api/rooms") //
@Transactional //
public class RoomController {
    private final RoomDao roomDao;
    private final WindowDao windowDao;
    private final SensorDao sensorDao;


    public RoomController(RoomDao roomDao, WindowDao windowDao, SensorDao sensorDao) {
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.sensorDao = sensorDao;
    }

    // Get Room List
    @GetMapping //
    public List<Room> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomMapper::of)
                .sorted(Comparator.comparing(Room::name))
                .collect(Collectors.toList());  //
    }

    @GetMapping(path = "/{id}")
    public Room findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomMapper::of).orElse(null); //
    }

    // Add a Room
    @PostMapping //
    public ResponseEntity<Sensor> create(@RequestBody RoomEntity room) { //
        RoomEntity entity = new RoomEntity(room.getName(), room.getCurrentTemperature(), room.getTargetTemperature());
// I don't know why the saved here won't work
        RoomEntity saved = roomDao.save(entity);
        return ResponseEntity.ok(SensorMapper.of(saved.getCurrentTemperature()));
    }

    // Update a Room
    @PutMapping(path = "/{id}") //
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody RoomCommand room) {
        RoomEntity entity = roomDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setName(room.name());
        return ResponseEntity.ok(RoomMapper.of(entity));
    }

    // Delete a room and windows and heaters
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteWindowsByRoomId(id);
        //heaterDao.deleteHeatersByRoomId(id); I would have implemented it but I chose not to pursue the creation of Heater and Building appliances.
        //It was not clear to me whether it was compulsory or not and did it to a certain extent but chose to focus on making things work without those additional classes.
        roomDao.deleteById(id);
    }

    // Close all the windows in the room
    @PutMapping(path = "/{id}/closeWindows")
    public ResponseEntity<Room> closeWindows(@PathVariable Long id) {
        RoomEntity entity = roomDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        windowDao.updateWindowStatusByRoomId(id, 0.0);
        entity.setWindows(roomDao.findById(id).get().getWindows());
        return ResponseEntity.ok(RoomMapper.of(entity));
    }

    /*// Open all the windows in the room
    @PutMapping(path = "/{id}/closeWindows")
    public ResponseEntity<Room> openWindows(@PathVariable Long id) {
        RoomEntity entity = roomDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        windowDao.updateWindowStatusByRoomId(id, 1.1);
        entity.setWindows(roomDao.findById(id).get().getWindows());
        return ResponseEntity.ok(RoomMapper.of(entity));
    }
*/

}