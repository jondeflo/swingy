package ru.school21.swingy.Model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.util.Set;
import java.util.logging.Level;

import static ru.school21.swingy.Swingy.view;

public class InputValidator {

    @NotEmpty(message = "Hero name must not be empty")
    @Size(min = 3, max = 20, message = "Hero name must contains between 3 and 20 symbols")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Hero name must contains letters and numbers only")
    public String name;

    @Pattern(regexp = "[123]", message = "Choose proper option (1, 2 or 3)")
    private String type;

    @Pattern(regexp = "[wsad]", message = "Choose proper direction: North (w), South (s), West (a) or East (d)")
    public String inGameInput;

    @Pattern(regexp = "[yn]", message = "Press 'y' or 'n'")
    public String yesNo;


    public InputValidator(String name, String type, String inGameInput, String yesNo) {
        this.name = name;
        this.type = type;
        this.inGameInput = inGameInput;
        this.yesNo = yesNo;
    }

    public boolean getValidationResult(InputValidator inst)
    {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<InputValidator>> constraintViolations = validator.validate(inst);
        if (constraintViolations.size() > 0)
        {
            for (ConstraintViolation<InputValidator> cv : constraintViolations)
                view.writeOut(view.COLOR_WARNING + cv.getMessage() + view.COLOR_RESET);
            return false;
        }
        else
            return true;
    }
}
