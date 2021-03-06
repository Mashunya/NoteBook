package notebook.validators;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class ValidatorRegistry {
    private Map<Enum, Validator> validatorMap;
    private static ValidatorRegistry validatorRegistry;

    private ValidatorRegistry() {
        validatorMap = new HashMap<>();
        validatorMap.put(ValidatorNames.Required, new RequiredValidator());
        validatorMap.put(ValidatorNames.NotNegativeNumber, new NotNegativeNumberValidator());
        validatorMap.put(ValidatorNames.StringLength, new StringLengthValidator());
    }

    public static ValidatorRegistry getInstance() {
        if(validatorRegistry == null) {
            validatorRegistry = new ValidatorRegistry();
        }
        return validatorRegistry;
    }

    public Validator getValidator(Enum validatorName) {
        return validatorMap.get(validatorName);
    }
}
