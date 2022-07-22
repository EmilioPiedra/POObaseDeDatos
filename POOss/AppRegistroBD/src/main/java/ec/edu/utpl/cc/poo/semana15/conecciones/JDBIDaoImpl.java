package ec.edu.utpl.cc.poo.semana15.conecciones;
import ec.edu.utpl.cc.poo.semana15.model.Estudiante;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

public class JDBIDaoImpl implements IDao {
    private Jdbi jdbi;
    private DBConfig config;

    public JDBIDaoImpl(DBConfig config) {
        this.config = config;
        jdbi = Jdbi.create(
                config.getJdbcURL(),
                config.getUsername(),
                config.getPasword());

    }

    @Override
    public boolean create(Estudiante estudiante) {
        String sqlInsert = """
                INSERT INTO ESTUDIANTE (
                CEDULA,
                APELLIDO, 
                NOMBRE,    
                EMAIL,  
                EDAD,  
                PESO)  VALUES(
                :cedula, 
                :apellido, 
                :nombre,  
                :email,
                :edad, 
                :peso) 
                """;
        int rows = jdbi.withHandle(handle ->
                handle.createUpdate(sqlInsert)
                .bindBean(estudiante)
                .execute());
        return rows >0;
    }

    @Override
    public Estudiante read(String cedula) {
        String sqlSelect = "SELECT * FROM ESTUDIANTE WHERE CEDULA = :cedula";
       return jdbi.withHandle(handle -> (handle.createQuery(sqlSelect)
                .bind("cedula",cedula)
                .mapToBean(Estudiante.class)
                .one())
        );
    }

    @Override
    public boolean update(Estudiante estudiante) {
        String sqlUpdate= """
                UPDATE ESTUDIANTE SET
                CEDULA = :cedula,
                APELLIDO = :apellido,
                NOMBRE = :nombre,
                EDAD = :edad,
                PESO = :peso
                WHERE ID = :id
                """;
       int rowsUpdate = jdbi.withHandle(handle -> handle.createUpdate(sqlUpdate)
                .bindBean(estudiante)
                .execute()
        );
        return rowsUpdate>0;
    }

    @Override
    public boolean delete(int id) {
        String sqlDelete = "DELETE FROM ESTUDIANTE WHERE ID = :id";
        int rowsDelete =  jdbi.withHandle(handle -> handle.createUpdate(sqlDelete)
                .bind("id",id)
                .execute()
        );
        return rowsDelete>0;
    }

    @Override
    public List<Estudiante> readAllData() {
        String sqlSelect = "SELECT * FROM ESTUDIANTE";
       return jdbi.withHandle(handle -> handle
                .createQuery(sqlSelect)
               .mapToBean(Estudiante.class)
               .list());
    }
}
