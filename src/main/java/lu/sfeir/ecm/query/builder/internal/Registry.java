package lu.sfeir.ecm.query.builder.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class Registry implements Renderable, HasRenderContext
{

    private final RenderContext context;
    private final List<Renderable> renderables;

    public Registry(final RenderContext context, final int capacity)
    {
        this.context = context;
        renderables = new ArrayList<Renderable>(capacity);
    }

    public List<Renderable> getRenderables()
    {
        return Collections.unmodifiableList(renderables);
    }

    public boolean hasItems()
    {
        return !renderables.isEmpty();
    }

    public <T extends Renderable> T register(final T renderable)
    {
        renderables.add(renderable);
        return renderable;
    }

    @Override
    public void accept(final Renderer renderer)
    {
        if (hasItems())
        {
            for (final Renderable renderable : renderables)
            {
                renderable.accept(renderer);
            }
        }
    }

    @Override
    public RenderContext getRenderContext()
    {
        return context;
    }
}
