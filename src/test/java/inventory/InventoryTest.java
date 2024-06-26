package inventory;

import inventory.model.InhousePart;
import inventory.model.Part;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InventoryTest {
    private Part part;
    private InventoryRepository repo = new InventoryRepository();;

    @Test
    void TC1_ECP() {
        part = new InhousePart(1, "part1", 20, 3, 1,10, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize + 1;
    }

    @Test
    void TC2_ECP() {
        part = new InhousePart(3, "", 2020, 5, 3,9, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("A name has not been entered. ");
    }

    @Test
    //@Disabled
    void TC6_ECP() {
        part = new InhousePart(5, "part5", 205, 15, 3,10, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("Inventory level is higher than the maximum value. ");
    }

    @Test
    void TC8_ECP() {
        part = new InhousePart(7, "part7", 12345, 0, 3,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("Inventory level must be greater than 0. Inventory level is lower than minimum value. ");
    }

    @Test
    void TC3_BVA() {
        part = new InhousePart(1, "part1", 20, 3, 1,10, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize + 1;
    }

    @Test
    void TC22_BVA() {
        part = new InhousePart(1, "part", 123, 1, 1,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize + 1;
    }

    @Test
    void TC1_BVA() {
        part = new InhousePart(1, "", 123, 3, 2,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("A name has not been entered. ");
    }

    @Test
    void TC4_BVA() {
        part = new InhousePart(1, "part", 123, -1, 3,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("Inventory level must be greater than 0. Inventory level is lower than minimum value. ");
    }
}