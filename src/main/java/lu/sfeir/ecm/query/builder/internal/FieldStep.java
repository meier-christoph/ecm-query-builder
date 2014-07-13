package lu.sfeir.ecm.query.builder.internal;

import lu.sfeir.ecm.query.builder.Condition;
import lu.sfeir.ecm.query.builder.Field;

import java.util.Date;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class FieldStep implements Field
{

    private final String field;

    public FieldStep(final String field)
    {
        this.field = field;
    }

    public String getField()
    {
        return field;
    }

    @Override
    public Condition is(final String value)
    {
        return matches(value);
    }

    @Override
    public Condition matches(final String value)
    {
        return new ConditionFieldMatches(this, value);
    }

    @Override
    public Condition before(final Date value)
    {
        return new ConditionFieldBefore(this, value);
    }

    @Override
    public Condition after(final Date value)
    {
        return new ConditionFieldAfter(this, value);
    }

    @Override
    public Condition in(final String... values)
    {
        if (null == values) { return matches(null); }
        if (1 == values.length) { return matches(values[0]); }
        return new ConditionFieldIn(this, values);
    }
}
