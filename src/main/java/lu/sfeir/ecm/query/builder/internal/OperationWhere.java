package lu.sfeir.ecm.query.builder.internal;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class OperationWhere extends OperationBase
{

    public OperationWhere(final Registry registry, final Renderable nested)
    {
        super(registry, nested);
    }

    @Override
    public void accept(final Renderer renderer)
    {
        renderer.append('(');
        getNested().accept(renderer);
        renderer.append(')');
    }
}
