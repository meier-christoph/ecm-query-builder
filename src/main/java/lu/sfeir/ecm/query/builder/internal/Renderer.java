package lu.sfeir.ecm.query.builder.internal;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public interface Renderer
{

    Renderer append(char character);

    Renderer append(String str);

    Renderer append(StringBuilder str);

}
