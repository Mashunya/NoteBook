package NoteBook.Validators;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class ValidatorRegistry {
    private Map<String, Validator> validatorMap;
    private static ValidatorRegistry validatorRegistry;

    private ValidatorRegistry() {
        validatorMap = new HashMap<>();
        validatorMap.put("Required", new RequiredValidator());
        validatorMap.put("NotNegativeNumber", new NotNegativeNumberValidator());
        validatorMap.put("StringLength", new StringLengthValidator());
    }

    public static ValidatorRegistry getInstance() {
        if(validatorRegistry == null) {
            validatorRegistry = new ValidatorRegistry();
        }
        return validatorRegistry;
    }

    public Validator getValidator(String validatorName) {
        return validatorMap.get(validatorName);
    }
}
