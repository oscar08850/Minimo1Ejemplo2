import edu.upc.dsa.clases.Objeto;
import edu.upc.dsa.clases.User;
import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestGameManager {


    private static Logger logger = Logger.getLogger(TestGameManager.class);
    GameManager pm = GameManagerImpl.getInstance();

    @Before
    public void SetUp() {


        //
        Objeto objeto1 = new Objeto("Espada","1");
        Objeto objeto2 = new Objeto("Escudo","2");
        Objeto objeto3 = new Objeto("Moneda","3");

        //Metemos objetos en la lista
        pm.addObjectALista(objeto1);
        pm.addObjectALista(objeto2);
        pm.addObjectALista(objeto3);


        //Creamos los usuarios (addUser)
        //Añadimos los usuarios al HashMap
        pm.addUser("Oscar","Vilamitjana", "1");
        pm.addUser("Alex","Moya", "2");
        pm.addUser("Toni","Oller", "3");

        pm.addObjectToUserUsandoIdObjeto("3","1"); //Toni tiene Espada
    }

    //Método de test para añadir un usuario en el sistema
    // y verificar el número de usuarios.
    @Test
    public void test1(){
        logger.info("ANTES Hay estos usuario " + pm.numeroUsuarios());
        pm.addUser("Juan","Rubio", "8");
        User user = pm.getUserById("8");
        logger.info("Usuario añadido: " + user);
        logger.info("Hay estos usuario " + pm.numeroUsuarios());
    }

    //método de test para añadir un objeto a un usuario y verificar el
    //número de objetos asociados a un usuario
    @Test
    public void test2(){
        User user = pm.getUserById("3");
        logger.info("Antes tiene: " + pm.numeroObjetosUsuario("3")); //Espada
        user.muestraObjetosUser();

        pm.addObjectToUserUsandoIdObjeto("3","2");
        logger.info("Despues tiene: " + pm.numeroObjetosUsuario("3")); //Espada y Escudo
        user.muestraObjetosUser();
    }


    @After
    public void reset(){
        pm.clear();
    }
}
