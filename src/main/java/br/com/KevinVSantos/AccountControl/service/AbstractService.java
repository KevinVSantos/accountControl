package br.com.KevinVSantos.AccountControl.service;

import br.com.KevinVSantos.AccountControl.domain.entity.AbstractEntity;
import br.com.KevinVSantos.AccountControl.handler.exception.EntityAlreadyExistsException;
import br.com.KevinVSantos.AccountControl.handler.exception.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Data
public abstract class AbstractService<Id, Entity extends AbstractEntity<Id>, Repository extends JpaRepository<Entity, Id>> {

    @Autowired
    private Repository repository;

    public Entity findById(Id id){
        var result = repository.findById(id);

        if(result.isEmpty())
        {
            throw new EntityNotFoundException();
        }

        return result.get();
    }

    public List<Entity> findAll(){
        return repository.findAll();
    }

    public Entity create(Entity entity){

        var result = repository.findById(entity.getGenericId());

        if(result.isPresent())
        {
            throw new EntityAlreadyExistsException();
        }

        return repository.save(entity);
    }

    public Entity update(Entity entity){
        var old = repository.findById(entity.getGenericId());

        if(old.isEmpty())
        {
            throw new EntityNotFoundException();
        }

        return repository.save(entity);
    }

    public void delete(Id id){
        var old = repository.findById(id);

        if(old.isEmpty())
        {
            throw new EntityNotFoundException();
        }

        repository.deleteById(id);
    }

}
