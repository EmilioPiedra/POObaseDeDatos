package ec.edu.utpl.cc.poo.semana15.conecciones;

import ec.edu.utpl.cc.poo.semana15.model.Estudiante;

import java.util.List;

public interface IDao {
    //CRUD
    //(crear, leer, actulizar, borrar)
    boolean create(Estudiante estudiante);
    Estudiante read(String cedula);
    boolean update(Estudiante estudiante);
    boolean delete(int id);
    List<Estudiante> readAllData();
}
