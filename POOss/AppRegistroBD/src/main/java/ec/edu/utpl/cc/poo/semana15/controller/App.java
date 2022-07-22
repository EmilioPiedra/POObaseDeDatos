package ec.edu.utpl.cc.poo.semana15.controller;

import ec.edu.utpl.cc.poo.semana15.conecciones.DBConfig;
import ec.edu.utpl.cc.poo.semana15.conecciones.JDBIDaoImpl;
import ec.edu.utpl.cc.poo.semana15.model.Estudiante;

public class App {
    public static void main(String[] args) {
        DBConfig config = new DBConfig("jdbc:h2:C:\\Users\\SALA A\\h2BBD","sa","");
        //jdbc:h2:C:\Users\SALA A\h2BBD
        JDBIDaoImpl jbdiDao = new JDBIDaoImpl(config);
        Estudiante estudiante = new Estudiante();
        estudiante.setApellido("Piedra");
        estudiante.setCedula("1105253890");
        estudiante.setEdad(19);
        estudiante.setPeso(75);
        estudiante.setEmail("espiedra1@utpl.edu.ec");
        Estudiante estbase = jbdiDao.read ("1105253890");
        System.out.println(estbase.getId()+estbase.getApellido()+estbase.getNombre());
    }
}
