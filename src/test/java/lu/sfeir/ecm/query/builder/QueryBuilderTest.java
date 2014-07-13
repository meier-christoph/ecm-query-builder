package lu.sfeir.ecm.query.builder;

import org.junit.Test;

import static lu.sfeir.ecm.query.builder.QueryBuilder.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Christoph Meier [meier.c@sfeir.lu]
 */
public class QueryBuilderTest
{

    @Test
    public void test_select_from() throws Exception
    {
        assertThat(select().from("T1").eval().toString(), is("(dDocType <matches> `T1`)"));

        assertThat(select().from("T1", "T2").eval().toString(),
                   is("(dDocType <matches> `T1` <or> dDocType <matches> `T2`)"));
    }

    @Test
    public void test_select_where() throws Exception
    {
        assertThat(select().where(field("F1").matches("1")).eval().toString(),
                   is("(F1 <matches> `1`)"));
    }

    @Test
    public void test_select_where_has() throws Exception
    {
        assertThat(select().where(has(field("F1").is("1")).and(field("F2").is("2"))
                                                          .and(field("F3").is("3")))
                           .eval()
                           .toString(),
                   is("((F1 <matches> `1`) <and> (F2 <matches> `2`) <and> (F3 <matches> `3`))"));

        assertThat(select().where(has(field("F1").is("1")).or(field("F2").is("2"))
                                                          .and(field("F3").is("3")))
                           .and(has(field("F4").is("4")).or(field("F5").is("5")))
                           .eval()
                           .toString(),
                   is("((F1 <matches> `1`) <or> (F2 <matches> `2`) <and> (F3 <matches> `3`)) <and> ((F4 <matches> `4`) <or> (F5 <matches> `5`))"));

        assertThat(select().from("T1")
                           .where(has(field("F1").is("1")).and(field("F2").is("2"))
                                                          .and(field("F3").is("3")))
                           .eval()
                           .toString(),
                   is("(dDocType <matches> `T1`) <and> ((F1 <matches> `1`) <and> (F2 <matches> `2`) <and> (F3 <matches> `3`))"));
    }

    @Test
    public void test_select_where_and_or() throws Exception
    {
        assertThat(select().where(field("F1").matches("1"))
                           .and(field("F2").matches("2"))
                           .eval()
                           .toString(), is("(F1 <matches> `1`) <and> (F2 <matches> `2`)"));

        assertThat(select().where(field("F1").matches("1"))
                           .and(field("F2").matches("2"))
                           .and(field("F3").matches("3"))
                           .eval()
                           .toString(),
                   is("(F1 <matches> `1`) <and> (F2 <matches> `2`) <and> (F3 <matches> `3`)"));

        assertThat(select().where(field("F1").matches("1"))
                           .and(field("F2").matches("2"))
                           .and(field("F3").matches("3"))
                           .and(field("F4").matches("4"))
                           .eval()
                           .toString(),
                   is("(F1 <matches> `1`) <and> (F2 <matches> `2`) <and> (F3 <matches> `3`) <and> (F4 <matches> `4`)"));

        assertThat(select().where(field("F1").matches("1"))
                           .and(field("F2").matches("2"))
                           .or(field("F3").matches("3"))
                           .and(field("F4").matches("4"))
                           .eval()
                           .toString(),
                   is("(F1 <matches> `1`) <and> (F2 <matches> `2`) <or> (F3 <matches> `3`) <and> (F4 <matches> `4`)"));
    }

    @Test
    public void test_select_from_where() throws Exception
    {
        assertThat(select().from("T1").where(field("F1").matches("1")).eval().toString(),
                   is("(dDocType <matches> `T1`) <and> (F1 <matches> `1`)"));

        assertThat(select().from("T1", "T2").where(field("F1").matches("1")).eval().toString(),
                   is("(dDocType <matches> `T1` <or> dDocType <matches> `T2`) <and> (F1 <matches> `1`)"));
    }

    @Test
    public void test_select_in() throws Exception
    {
        assertThat(select().from("T1").where(field("F1").in("1", "2", "3")).eval().toString(),
                   is("(dDocType <matches> `T1`) <and> (F1 <matches> `1` <or> F1 <matches> `2` <or> F1 <matches> `3`)"));
    }

    @Test
    public void test_select_from_where_and_or() throws Exception
    {
        assertThat(select().from("T1")
                           .where(field("F1").matches("1"))
                           .and(field("F2").matches("2"))
                           .eval()
                           .toString(),
                   is("(dDocType <matches> `T1`) <and> (F1 <matches> `1`) <and> (F2 <matches> `2`)"));

        assertThat(select().from("T1", "T2")
                           .where(field("F1").matches("1"))
                           .and(field("F2").matches("2"))
                           .and(field("F3").matches("3"))
                           .eval()
                           .toString(),
                   is("(dDocType <matches> `T1` <or> dDocType <matches> `T2`) <and> (F1 <matches> `1`) <and> (F2 <matches> `2`) <and> (F3 <matches> `3`)"));
    }
}
