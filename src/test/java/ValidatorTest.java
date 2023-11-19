
import org.example.Utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
    }
    @Test
    public void testValidateName_ValidName() {
        assertEquals(false, validator.validateName("JohnD_oe 789git "));
    }

    @Test
    public void testValidateZip_ValidZip() {
        assertEquals(false, validator.validateZip("12mm345"), "zip code containing chars");
    }

    @Test
    public void testValidateState_ValidState() {
        assertEquals(false, validator.validateState(null), "state containing null");
    }

    @Test
    public void testValidateCity_ValidCity() {
        assertEquals(false, validator.validateCity("      "), "city containing spaces");
    }

}
