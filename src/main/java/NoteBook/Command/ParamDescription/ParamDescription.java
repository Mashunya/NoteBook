package NoteBook.Command.ParamDescription;

import NoteBook.Parsers.Parser;
import NoteBook.Validators.Validator;

import java.util.ArrayList;

/**
 * Created by Маша on 15.06.2017.
 */
public class ParamDescription {
    private boolean required;
    private ArrayList<Validator> validators = new ArrayList<>();
    private Parser parser;
    private Object defaultValue;

    private ParamDescription() {}

    public boolean isRequired() {
        return required;
    }

    public ArrayList<Validator> getValidators() {
        return validators;
    }

    public Parser getParser() {
        return parser;
    }

    public Object getDefaultValue() {
        return defaultValue;
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
            ParamDescription.this.validators = validators;
            return this;
        }

        public Builder parser(Parser parser) {
            ParamDescription.this.parser = parser;
            return this;
        }

        public Builder defaultValue(Object defaultValue) {
            ParamDescription.this.defaultValue = defaultValue;
            return this;
        }

        public ParamDescription build() {
            return ParamDescription.this;
        }

    }
}
