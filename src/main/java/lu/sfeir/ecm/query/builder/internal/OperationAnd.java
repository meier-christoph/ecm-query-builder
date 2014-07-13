package lu.sfeir.ecm.query.builder.internal;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class OperationAnd extends OperationBase
{

    public OperationAnd(final Registry registry, final Renderable nested)
    {
        super(registry, nested);
    }

    @Override
    public void accept(final Renderer renderer)
    {
        renderer.append(" <and> (");
        getNested().accept(renderer);
        renderer.append(')');
    }
}
