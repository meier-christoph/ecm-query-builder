package lu.sfeir.ecm.query.builder.internal;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class OperationOr extends OperationBase
{

    public OperationOr(final Registry registry, final Renderable nested)
    {
        super(registry, nested);
    }

    @Override
    public void accept(final Renderer renderer)
    {
        renderer.append(" <or> (");
        getNested().accept(renderer);
        renderer.append(')');
    }
}
