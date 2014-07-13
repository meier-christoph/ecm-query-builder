package lu.sfeir.ecm.query.builder.internal;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public interface Renderable
{

    void accept(Renderer renderer);
}
