package lu.sfeir.ecm.query.builder;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public interface From extends SelectQuery
{

    <C extends Condition> Operation where(C condition);

    <O extends Operation> Operation where(O operation);
}
