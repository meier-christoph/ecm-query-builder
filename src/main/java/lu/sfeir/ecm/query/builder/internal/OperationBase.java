package lu.sfeir.ecm.query.builder.internal;

import lu.sfeir.ecm.query.builder.Condition;
import lu.sfeir.ecm.query.builder.Operation;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public abstract class OperationBase implements Operation, HasRegistry, Renderable
{

    private final Registry registry;
    private final Renderable nested;

    public OperationBase(final Registry registry, final Renderable nested)
    {
        this.registry = registry;
        this.nested = nested;
    }

    @Override
    public Registry getRegistry()
    {
        return registry;
    }

    public Renderable getNested()
    {
        return nested;
    }

    @Override
    public <C extends Condition> Operation and(final C condition)
    {
        return getRegistry().register(new OperationAnd(getRegistry(), (Renderable) condition));
    }

    @Override
    public <O extends Operation> Operation and(final O operation)
    {
        final Registry children = ((HasRegistry) operation).getRegistry();
        return getRegistry().register(new OperationAnd(getRegistry(), children));
    }

    @Override
    public <C extends Condition> Operation or(final C condition)
    {
        return getRegistry().register(new OperationOr(getRegistry(), (Renderable) condition));
    }

    @Override
    public <O extends Operation> Operation or(final O operation)
    {
        final Registry children = ((HasRegistry) operation).getRegistry();
        return getRegistry().register(new OperationOr(getRegistry(), children));
    }

    @Override
    public StringBuilder eval()
    {
        return getRegistry().getRenderContext().render();
    }
}
