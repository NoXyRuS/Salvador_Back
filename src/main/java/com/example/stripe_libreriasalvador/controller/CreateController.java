package com.example.stripe_libreriasalvador.controller;

import com.example.stripe_libreriasalvador.exception.ValidationException;
import com.example.stripe_libreriasalvador.model.Create;
import com.example.stripe_libreriasalvador.repository.CreateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class CreateController {

    private final CreateRepository createRepository;

    public CreateController(CreateRepository createRepository){
        this.createRepository=createRepository;
    }

    //Endopint (url): http://localhost:8080/api/v1/employees
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/usuarios")
    public ResponseEntity<List<Create>> getAllCreates(){
        return new ResponseEntity<List<Create>>(createRepository.findAll(), HttpStatus.OK);
    }


    //Endopint (url): http://localhost:8080/api/v1/employees
    //Method: POST
    @Transactional
    @PostMapping("/usuarios")
    public ResponseEntity<Create> createCreate(@RequestBody Create create){
        existsCreateByNameOrDni(create);
        validateCreate(create);
        return new ResponseEntity<Create>(createRepository.save(create), HttpStatus.CREATED);
    }




    //Endopint (url): http://localhost:8080/api/v1/employees
    //Method: Delete
    @Transactional
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteCreate(@PathVariable Long id) {
        createRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @Transactional
    @PutMapping("/edit-usuario/{id}")
    public ResponseEntity<Create> updateCreate(@PathVariable Long id, @RequestBody Create create) {
        Create existingCreate = createRepository.findById(id).orElse(null);
        if (existingCreate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualizar los campos del experto existente con los datos del experto enviado en la solicitud

        if ( create.getName() != null){
            existingCreate.setName(create.getName());
        }

        if ( create.getEmail() != null){
            existingCreate.setEmail(create.getEmail());
        }
        if ( create.getPassword() != null){
            existingCreate.setPassword(create.getPassword());
        }


        // Guardar los cambios en la base de datos
        Create updatedCreate = createRepository.save(existingCreate);

        return new ResponseEntity<>(updatedCreate, HttpStatus.OK);
    }
    //Reglas de negocio
    //Validaciones
    private void validateCreate(Create create){
        if(create.getName()==null || create.getName().trim().isEmpty()){
            throw new ValidationException("El nombre de usuario es obligatorio");
        }

        if(create.getName().length()>30){
            throw new ValidationException("El nombre de usuario no debe exceder los 30 caracteres");
        }

        if(create.getEmail()==null || create.getEmail().trim().isEmpty()){
            throw new ValidationException("El correo es obligatorio");
        }

        if(create.getPassword()==null || create.getPassword().trim().isEmpty()){
            throw new ValidationException("La contraseña  es obligatoria");
        }

        if(create.getPassword().length()<8){
            throw new ValidationException("La contraseña debe tener minimo 8 caracteres");
        }

    }

    //No se debe permitir el registro de un empleado con el mismo nombre y dni.
    private void existsCreateByNameOrDni(Create create){

        if(createRepository.existsByEmail(create.getEmail())){
            throw new ValidationException("Ese correo ya está registrado");
        }

    }



}
