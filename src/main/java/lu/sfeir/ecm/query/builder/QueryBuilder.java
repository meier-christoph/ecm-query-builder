package lu.sfeir.ecm.query.builder;

import lu.sfeir.ecm.query.builder.internal.FieldStep;
import lu.sfeir.ecm.query.builder.internal.NotStep;
import lu.sfeir.ecm.query.builder.internal.OperationWhere;
import lu.sfeir.ecm.query.builder.internal.Registry;
import lu.sfeir.ecm.query.builder.internal.Renderable;
import lu.sfeir.ecm.query.builder.internal.SelectStep;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public final class QueryBuilder
{

    private QueryBuilder()
    {
    }

    public static Select select()
    {
        return new SelectStep();
    }

    public static Field field(final String field)
    {
        return new FieldStep(field);
    }

    public static <C extends Condition> Operation has(final C condition)
    {
        final Registry registry = new Registry(null, 5);
        return registry.register(new OperationWhere(registry, (Renderable) condition));
    }

    public static Not not()
    {
        return new NotStep();
    }
}
