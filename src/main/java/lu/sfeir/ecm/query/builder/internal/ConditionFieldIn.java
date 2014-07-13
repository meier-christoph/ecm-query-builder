package lu.sfeir.ecm.query.builder.internal;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class ConditionFieldIn extends ConditionField<String[]>
{

    public ConditionFieldIn(final FieldStep fieldStep, final String[] value)
    {
        super(fieldStep, value);
    }

    @Override
    public void accept(final Renderer renderer)
    {
        boolean first = true;
        for (final String value : getValue())
        {
            if (!first)
            {
                renderer.append(" <or> ");
            }
            renderer.append(getField()).append(" <matches> `").append(value).append('`');
            first = false;
        }
    }
}
