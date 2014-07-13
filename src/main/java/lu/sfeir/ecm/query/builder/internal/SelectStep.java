package lu.sfeir.ecm.query.builder.internal;

import lu.sfeir.ecm.query.builder.Condition;
import lu.sfeir.ecm.query.builder.From;
import lu.sfeir.ecm.query.builder.Operation;
import lu.sfeir.ecm.query.builder.Select;

import java.util.Arrays;
import java.util.List;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class SelectStep implements Select, Renderer, RenderContext, HasRenderContext, HasRegistry
{

    private StringBuilder builder;
    private final Registry registry;

    public SelectStep()
    {
        this.registry = new Registry(this, 16);
    }

    @Override
    public From from(final String... documentTypes)
    {
        return from(Arrays.asList(documentTypes));
    }

    @Override
    public From from(final List<String> documentTypes)
    {
        return getRegistry().register(new FromStep(getRegistry(), documentTypes));
    }

    @Override
    public <C extends Condition> Operation where(final C condition)
    {
        return getRegistry().register(new OperationWhere(getRegistry(), (Renderable) condition));
    }

    @Override
    public <O extends Operation> Operation where(final O operation)
    {
        final Registry children = ((HasRegistry) operation).getRegistry();
        return getRegistry().register(new OperationWhere(getRegistry(), children));
    }

    @Override
    public Renderer append(final char character)
    {
        builder.append(character);
        return this;
    }

    @Override
    public Renderer append(final String str)
    {
        builder.append(str);
        return this;
    }

    @Override
    public Renderer append(final StringBuilder str)
    {
        builder.append(str);
        return this;
    }

    @Override
    public StringBuilder render()
    {
        builder = new StringBuilder(64);
        getRegistry().accept(this);
        return builder;
    }

    @Override
    public Registry getRegistry()
    {
        return registry;
    }

    @Override
    public RenderContext getRenderContext()
    {
        return this;
    }
}
