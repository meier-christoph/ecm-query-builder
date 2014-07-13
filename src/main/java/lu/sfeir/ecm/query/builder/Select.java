package lu.sfeir.ecm.query.builder;

import java.util.List;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public interface Select
{

    From from(String... documentTypes);

    From from(List<String> documentTypes);

    <C extends Condition> Operation where(C condition);

    <O extends Operation> Operation where(O operation);

}
