package de.tuda.dmdb.sql;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.sql.error.Error;
import de.tuda.dmdb.sql.statement.SelectStatement;
import de.tuda.dmdb.sql.statement.Statement;
import de.tuda.dmdb.storage.AbstractRecord;
import org.junit.Assert;

public class MyEquiJoinTest extends TestCase {
    private Statement compile(String sql){
        SQLCompiler compiler = new SQLCompiler();
        Statement stmt = compiler.compile(sql);

        if(compiler.getLastError().isError()){
            Assert.assertTrue("Execute failed: "+compiler.getLastError(), false);
        }

        return stmt;
    }

    private void execute(Statement stmt){
        Error lastError = stmt.execute();

        if(lastError.isError()){
            Assert.assertTrue("Execute failed: "+lastError, false);
        }
    }

    public void testSimpleProjection() {
        //CREATE TABLE statements and INSERT statements
        Statement stmt = this.compile("CREATE TABLE table1 ( a int, b int )");
        this.execute(stmt);

        stmt = this.compile("INSERT INTO table1 VALUES (1,1)");
        this.execute(stmt);
        stmt = this.compile("INSERT INTO table1 VALUES (2,1)");
        this.execute(stmt);
        stmt = this.compile("INSERT INTO table1 VALUES (3,2)");
        this.execute(stmt);

        stmt = this.compile("CREATE TABLE table2 ( c int, d int )");
        this.execute(stmt);

        stmt = this.compile("INSERT INTO table2 VALUES (1,1)");
        this.execute(stmt);
        stmt = this.compile("INSERT INTO table2 VALUES (1,2)");
        this.execute(stmt);
        stmt = this.compile("INSERT INTO table2 VALUES (2,2)");
        this.execute(stmt);
        SelectStatement selectStmt;

//Query statement
        selectStmt = (SelectStatement) this.compile("SELECT a, b, c, d FROM table1, table2 WHERE b=c");
        this.execute(selectStmt);

        for (AbstractRecord rec : selectStmt.getResultSet()) {
            System.out.println(rec);
        }

        Assert.assertEquals(5, selectStmt.getResultSet().size());
    }
}
