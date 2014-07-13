package lu.sfeir.ecm.query.builder.internal;

import lu.sfeir.ecm.query.builder.Field;
import lu.sfeir.ecm.query.builder.Not;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class NotStep implements Not
{

    @Override
    public Field field(final String field)
    {
        return new FieldStep(field);
    }
}
