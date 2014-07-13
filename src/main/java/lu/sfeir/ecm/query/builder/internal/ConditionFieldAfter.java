package lu.sfeir.ecm.query.builder.internal;

import java.util.Date;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class ConditionFieldAfter extends ConditionField<Date>
{

    public ConditionFieldAfter(final FieldStep fieldStep, final Date value)
    {
        super(fieldStep, value);
    }

    @Override
    public void accept(final Renderer renderer)
    {
        renderer.append(getField()).append(" >= `").append(getValue().toString()).append('`');
    }
}
