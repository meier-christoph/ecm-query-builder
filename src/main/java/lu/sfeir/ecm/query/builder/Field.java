package lu.sfeir.ecm.query.builder;

import java.util.Date;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public interface Field
{

    Condition is(String value);

    Condition matches(String value);

    Condition before(Date value);

    Condition after(Date value);

    Condition in(String... values);
}
