package lu.sfeir.ecm.query.builder.internal;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public abstract class ConditionField<T> extends ConditionBase
{

    private final FieldStep fieldStep;
    private final T value;

    public ConditionField(final FieldStep fieldStep, final T value)
    {
        super();
        this.fieldStep = fieldStep;
        this.value = value;
    }

    public String getField()
    {
        return fieldStep.getField();
    }

    public T getValue()
    {
        return value;
    }

}
