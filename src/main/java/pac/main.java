package pac;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class main {
    public static void main(String[] args) {

        //Usamos Hibernate para definir las interfaces
        Configuration cfg = new Configuration().configure();

        SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        Session session = sessionFactory.openSession();

        //Creacion de modulos
        Modulo m1 = new Modulo();
        m1.setNombre("Programacion B");
        m1.setCodigo("M03B");
        Modulo m2 = new Modulo();
        m2.setNombre("Acceso a Datos");
        m2.setCodigo("M06");
        Modulo m3 = new Modulo();
        m3.setNombre("Desarrollo de aplicaciones moviles");
        m3.setCodigo("M08");
        Modulo m4 = new Modulo();
        m4.setNombre("Servicios y procesos");
        m4.setCodigo("M09");

        //Insertamos los modulos
        insertModulo(session, m1);
        insertModulo(session, m2);
        insertModulo(session, m3);
        insertModulo(session, m4);

        //Insertamos el profesor
        insertProfesor(session,"Albaro","Hombre");

        //Insertamos los alumnos
        Set<Modulo> set1 = new HashSet<>();
        set1.add(m1);
        set1.add(m2);
        set1.add(m3);
        set1.add(m4);
        insertAlumno(session,"Juan","Espaniola",26,"Hombre",set1);

        Set<Modulo> set2 = new HashSet<>();
        set2.add(m1);
        set2.add(m2);
        set2.add(m4);
        insertAlumno(session,"Pedro","Andorrana",21,"Hombre",set2);

        Set<Modulo> set3 = new HashSet<>();
        set2.add(m3);
        set2.add(m4);
        insertAlumno(session,"Marta","Espaniola",19,"Hombre",set3);

        Set<Modulo> set4 = new HashSet<>();
        set1.add(m2);
        set1.add(m3);
        set1.add(m4);
        insertAlumno(session,"Carla","Francesa",35,"Mujer",set4);


        //cerramos las conexiones
        session.close();
        sessionFactory.close();


    }

    //Funciones de insert

    public static void insertModulo(Session session, Modulo modulo){
        Transaction transaction = session.beginTransaction();
        session.save(modulo);
        transaction.commit();
        System.out.println("Insert into modulo, nombre: " + modulo.getNombre()+ ",  codigo: " + modulo.getNombre());
    }

    public static void insertAlumno(Session session, String nombre, String nacionalidad, int edad, String sexo, Set<Modulo> modulos ){
        Transaction transaction = session.beginTransaction();
        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setNacionalidad(nacionalidad);
        alumno.setEdad(edad);
        alumno.setSexo(sexo);
        alumno.setModulos(modulos);
        session.save(alumno);
        transaction.commit();
        System.out.println("Insert into alumno, nombre: "+ nombre +", nacionalidad: "+nacionalidad+ ", edad: "+ edad+", sexo: "+sexo+", modulos: " +modulos.size());
    }

    public static void insertProfesor(Session session, String nombre, String sexo){
        Transaction transaction = session.beginTransaction();
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setSexo(sexo);
        session.save(profesor);
        transaction.commit();
        System.out.println("Insert into profesor, nombre: " + nombre + ",  sexo: " + sexo);

    }


}
