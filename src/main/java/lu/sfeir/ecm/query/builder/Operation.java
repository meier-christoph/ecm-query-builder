package lu.sfeir.ecm.query.builder;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public interface Operation extends SelectQuery
{

    <C extends Condition> Operation and(C condition);

    <O extends Operation> Operation and(O operation);

    <C extends Condition> Operation or(C condition);

    <O extends Operation> Operation or(O operation);
}
