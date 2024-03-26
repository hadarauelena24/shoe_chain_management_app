import org.example.Model.Repository.UtilizatorRepo;
import org.example.Model.Utilizator;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class UtilizatorRepoTest {
    @Test
    public void testAddUtilizator() {
        UtilizatorRepo utilizatorRepo = new UtilizatorRepo();

        Utilizator utilizator = new Utilizator(10, "Dan", "Ina", "danina@yahoo.com", "ina9", "0755677888", "manager");

        boolean result = utilizatorRepo.addUtilizator(utilizator);

        assertTrue(result);
    }

    @Test
    public void test1FindByEmailSiParola() {
        // Cream un obiect de tip UtilizatorRepo pentru a testa metoda
        UtilizatorRepo utilizatorRepo = new UtilizatorRepo();

        // Apelăm metoda pentru a obține un rezultat
        Utilizator rezultat = utilizatorRepo.findByEmailSiParola("popion@gmail.com", "ion6");

        // Verificăm dacă rezultatul este diferit de null (adica metoda findByEmailSiParola returneaza un Utilizator)
        assertNotNull(rezultat);
        assertTrue(rezultat instanceof Utilizator);
        assertEquals("Pop", rezultat.getNume());
        assertEquals("Ion", rezultat.getPrenume());
    }
    @Test
    public void test2FindByEmailSiParola() {
        UtilizatorRepo utilizatorRepo = new UtilizatorRepo();
        Utilizator rezultat = utilizatorRepo.findByEmailSiParola("popion@gmail.com", "ion4");
        assertNull(rezultat);
        assertFalse(rezultat instanceof Utilizator);

    }
    @Test
    public void testDeleteUtilizator() {
        UtilizatorRepo utilizatorRepo = new UtilizatorRepo();
        boolean result = utilizatorRepo.deleteUtilizator(10);
        assertTrue(result);
    }
}
