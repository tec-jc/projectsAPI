package org.esfe.controladores;

import org.esfe.dtos.tarea.TareaCambiarEstado;
import org.esfe.dtos.tarea.TareaGuardar;
import org.esfe.dtos.tarea.TareaModificar;
import org.esfe.dtos.tarea.TareaSalida;
import org.esfe.servicios.interfaces.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    @Autowired
    private ITareaService tareaService;

    @GetMapping
    public ResponseEntity<Page<TareaSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<TareaSalida> tareas = tareaService.obtenerTodosPaginados(pageable);
        if(tareas.hasContent()){
            return ResponseEntity.ok(tareas);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<TareaSalida>> mostrarTodos(){
        List<TareaSalida> tareas = tareaService.obtenerTodos();
        if(!tareas.isEmpty()){
            return ResponseEntity.ok(tareas);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaSalida> mostrarPodId(@PathVariable Integer id){
        TareaSalida tarea = tareaService.obtenerPorId(id);
        if(tarea != null){
            return ResponseEntity.ok(tarea);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/proyecto/{id}")
    public ResponseEntity<List<TareaSalida>> mostrarPorProyecto(@PathVariable Integer id){
        List<TareaSalida> tareas = tareaService.obtenerPorProyectoId(id);
        if(!tareas.isEmpty()){
            return ResponseEntity.ok(tareas);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TareaSalida> crear(@RequestBody TareaGuardar tareaGuardar){
        TareaSalida tarea = tareaService.crear(tareaGuardar);
        if(tarea != null){
            return ResponseEntity.ok(tarea);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaSalida> editar(@PathVariable Integer id,  @RequestBody TareaModificar tareaModificar){
        TareaSalida tarea = tareaService.editar(tareaModificar);
        if(tarea != null){
            return ResponseEntity.ok(tarea);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PatchMapping
    public ResponseEntity<TareaSalida> cambiarEstado(@RequestBody TareaCambiarEstado tareaCambiarEstado){
        TareaSalida tarea = tareaService.cambiarEstado(tareaCambiarEstado);
        if(tarea != null){
            return ResponseEntity.ok(tarea);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        tareaService.eliminarPorId(id);
        return ResponseEntity.ok("Tarea eliminada correctamente");
    }
}
