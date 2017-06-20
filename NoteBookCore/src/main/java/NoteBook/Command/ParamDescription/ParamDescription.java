package NoteBook.Command.ParamDescription;

import NoteBook.Validators.Validator;
import NoteBook.Validators.ValidatorNames;
import NoteBook.Validators.ValidatorRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Маша on 15.06.2017.
 */
public class ParamDescription {
    private String paramName;
    private Class paramClass;
    private boolean required;
    private List<Validator> validators;
    private Object defaultValue;
    private int maxStringLength;

    private ParamDescription() {
        validators = new ArrayList<>();
        //TODO: Constants or enum?
        validators.add(ValidatorRegistry.getInstance().getValidator(ValidatorNames.Required));
        validators.add(ValidatorRegistry.getInstance().getValidator(ValidatorNames.StringLength));
    }

    public boolean isRequired() {
        return required;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public Class getParamClass() {
        return paramClass;
    }

    public int getMaxStringLength() {
        return maxStringLength;
    }

    public String getParamName() {
        return paramName;
    }

    public static Builder newBuilder() {
        return new ParamDescription().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder required(boolean required) {
            ParamDescription.this.required = required;
            return this;
        }

        public Builder validators(ArrayList<Validator> validators) {
            for(Validator validator: validators) {
                ParamDescription.this.validators.add(validator);
            }
            return this;
        }

        public Builder validators(Validator validator) {
            ParamDescription.this.validators.add(validator);
            return this;
        }

        public Builder defaultValue(Object defaultValue) {
            ParamDescription.this.defaultValue = defaultValue;
            return this;
        }

        public Builder paramClass(Class paramClass) {
            ParamDescription.this.paramClass = paramClass;
            return this;
        }

        public Builder length(int stringLength) {
            ParamDescription.this.maxStringLength = stringLength;
            return this;
        }

        public Builder paramName(String paramName) {
            ParamDescription.this.paramName = paramName;
            return this;
        }

        public ParamDescription build() {
            return ParamDescription.this;
        }

    }
}
