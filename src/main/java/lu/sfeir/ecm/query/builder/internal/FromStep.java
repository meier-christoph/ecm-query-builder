package lu.sfeir.ecm.query.builder.internal;

import lu.sfeir.ecm.query.builder.Condition;
import lu.sfeir.ecm.query.builder.From;
import lu.sfeir.ecm.query.builder.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class FromStep implements From, HasRegistry, Renderable
{

    private final Registry registry;
    private final List<String> documentTypes;

    public FromStep(final Registry registry, final List<String> documentTypes)
    {
        this.registry = registry;
        this.documentTypes = new ArrayList<String>(documentTypes);
    }

    @Override
    public Registry getRegistry()
    {
        return registry;
    }

    @Override
    public <C extends Condition> Operation where(final C condition)
    {
        getRegistry().register(new RenderableAnd());
        return getRegistry().register(new OperationWhere(getRegistry(), (Renderable) condition));
    }

    @Override
    public <O extends Operation> Operation where(final O operation)
    {
        getRegistry().register(new RenderableAnd());
        final Registry children = ((HasRegistry) operation).getRegistry();
        return getRegistry().register(new OperationWhere(getRegistry(), children));
    }

    @Override
    public void accept(final Renderer renderer)
    {
        renderer.append('(');
        boolean first = true;
        for (final String documentType : documentTypes)
        {
            if (!first)
            {
                renderer.append(" <or> ");
            }
            renderer.append("dDocType <matches> `").append(documentType).append('`');
            first = false;
        }
        renderer.append(')');
    }

    @Override
    public StringBuilder eval()
    {
        return getRegistry().getRenderContext().render();
    }

    private static class RenderableAnd implements Renderable
    {

        @Override
        public void accept(final Renderer renderer)
        {
            renderer.append(" <and> ");
        }
    }
}
