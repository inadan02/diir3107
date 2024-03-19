package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddPartTest {
    private String denumire;
    private int pret;
    private int numar_bucati;
    private int numar_minim;
    private int numar_maxim;
    private int id_masina;
    private InventoryRepository inventoryRepository;
    private InventoryService inventoryService;
    private InhousePart inhousePart;
    @BeforeEach
    void setUp() {
        try {
            PrintWriter myObj=new PrintWriter("C:\\Users\\Ina\\diir3107\\target\\classes\\data\\items_test.txt");
            myObj.print("");
            myObj.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inventoryRepository=new InventoryRepository();
        inventoryService=new InventoryService(inventoryRepository);
        numar_bucati=2;
        numar_minim=3;
        numar_maxim=4;
        id_masina=6;

    }

    @AfterEach
    void tearDown() {
    }


    @ParameterizedTest

    @DisplayName("Test ECP 1/Test BVA 6")

    @ValueSource(strings = { "parte", "obiect" })
    @RepeatedTest(3)
    @Tag("ecp")
    void TC1_ECP() {
        denumire="parte";
        pret=100;
        InhousePart inhousePart = new InhousePart(inventoryRepository.getAutoPartId(),denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);
        inventoryService.addInhousePart(denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);
        Part parte= inventoryRepository.getAllParts().get(0);
        Assertions.assertEquals(inhousePart.getName(),parte.getName());
        Assertions.assertEquals(inhousePart.getPrice(),parte.getPrice());
    }
    @Test
    @Tag("ecp")
    @DisplayName("Test ECP 2")
    void TC2_ECP() {
        denumire = "parte";
        pret = -100;
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            inventoryService.addInhousePart(denumire, pret, numar_bucati, numar_minim, numar_maxim, id_masina);
        });

        assertEquals("Price cannot be negative", exception.getMessage());

    }
    @Test()
    @Tag("ecp")
    @DisplayName("Test ECP 3")
    void TC3_ECP() {
        denumire="parte";
        String pret2="asd";
        Assertions.assertThrows(NumberFormatException.class,()->{inventoryService.addInhousePart(denumire,Integer.parseInt(pret2),numar_bucati,numar_minim,numar_maxim,id_masina);});
    }
    @Test()
    @Tag("ecp")
    @DisplayName("Test ECP 4")
    void TC4_ECP() {
        int denumire2=123;
        pret=300;
        Assertions.assertThrows(NumberFormatException.class,()->{inventoryService.addInhousePart(String.valueOf(denumire2),pret,numar_bucati,numar_minim,numar_maxim,id_masina);});
    }

    @Test
    @Tag("bva")
    @Timeout(5)
    @DisplayName("Test 5 BVA")
    void TC5_BVA() {
        denumire="";
        pret=100;
        Assertions.assertThrows(Exception.class,()->{inventoryService.addInhousePart(denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);});

    }
    @Test
    @Tag("bva")
    @Timeout(5)
    @DisplayName("Test 7 BVA")
    void TC7_BVA() {
        denumire="M";
        pret=0;
        Assertions.assertThrows(Exception.class,()->{inventoryService.addInhousePart(denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);});

    }
    @Test
    @Tag("bva")
    @Timeout(5)
    @DisplayName("Test 8 BVA")
    void TC8_BVA() {
        denumire="M";
        pret=2;
        InhousePart inhousePart = new InhousePart(inventoryRepository.getAutoPartId(),denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);
        inventoryService.addInhousePart(denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);
        List<Part> allParts = inventoryRepository.getAllParts();
        int lastIndex = allParts.size() - 1;
        Part lastPart = allParts.get(lastIndex);

        Assertions.assertEquals(inhousePart.getName(),lastPart.getName());
    }


}