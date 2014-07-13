package lu.sfeir.ecm.query.builder.internal;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class ConditionFieldMatches extends ConditionField<String>
{

    public ConditionFieldMatches(final FieldStep fieldStep, final String value)
    {
        super(fieldStep, value);
    }

    @Override
    public void accept(final Renderer renderer)
    {
        renderer.append(getField()).append(" <matches> `").append(getValue()).append('`');
    }
}
