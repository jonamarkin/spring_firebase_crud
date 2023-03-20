package com.atomarkin.firebasecrud.controller;

import com.atomarkin.firebasecrud.model.User;import com.atomarkin.firebasecrud.service.FirebaseCrudService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseCrudController {

  public FirebaseCrudService firebaseCrudService;

  public FirebaseCrudController() {
    this.firebaseCrudService = new FirebaseCrudService();
  }

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody User user)throws ExecutionException, InterruptedException {
    return ResponseEntity.ok(this.firebaseCrudService.create(user));
  }

  @GetMapping("/read")
  public ResponseEntity<?> read(@RequestParam String documentId) throws ExecutionException, InterruptedException {
    return ResponseEntity.ok(this.firebaseCrudService.read(documentId));
  }

  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody User user) throws ExecutionException, InterruptedException {
    return ResponseEntity.ok(this.firebaseCrudService.update(user));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@RequestParam String documentId) throws ExecutionException, InterruptedException {
    return ResponseEntity.ok(this.firebaseCrudService.delete(documentId));
  }

  @GetMapping("/readAll")
    public ResponseEntity<?> readAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(this.firebaseCrudService.readAll());
    }
}
