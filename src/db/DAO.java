package db;

import java.util.List;
import java.util.Optional;

public interface DAO <Obj, Key> {
	   
    public List<Obj> getAll();				// Returns a list of all the element of the table
    public Optional<Obj> getById(Key id);	// Gives the element corresponding to the id given

    public boolean    add(Obj value);		// Add an element to the db
    public boolean update(Obj value);		// Updates an element in the db
    public boolean delete(Obj value);		// Deletes an element in the db

}