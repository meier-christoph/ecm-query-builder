package lu.sfeir.ecm.query.builder.internal;

import java.util.Date;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class ConditionFieldBefore extends ConditionField<Date>
{

    public ConditionFieldBefore(final FieldStep fieldStep, final Date value)
    {
        super(fieldStep, value);
    }

    @Override
    public void accept(final Renderer renderer)
    {
        renderer.append(getField()).append(" <= `").append(getValue().toString()).append('`');
    }
}
