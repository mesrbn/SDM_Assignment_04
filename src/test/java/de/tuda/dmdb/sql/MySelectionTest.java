package de.tuda.dmdb.sql;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.sql.error.Error;
import de.tuda.dmdb.sql.statement.SelectStatement;
import de.tuda.dmdb.sql.statement.Statement;
import de.tuda.dmdb.storage.AbstractRecord;
import org.junit.Assert;

public class MySelectionTest extends TestCase {
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
        Statement stmt = this.compile("CREATE TABLE t3 ( a int, b int )");
        this.execute(stmt);

        stmt = this.compile("INSERT INTO t3 VALUES (1,1)");
        this.execute(stmt);
        stmt = this.compile("INSERT INTO t3 VALUES (2,1)");
        this.execute(stmt);
        stmt = this.compile("INSERT INTO t3 VALUES (3,2)");
        this.execute(stmt);

        SelectStatement selectStmt;

//First Query statement
        selectStmt = (SelectStatement) this.compile("SELECT a, b FROM t3 WHERE a = 1");
        this.execute(selectStmt);

        for (AbstractRecord rec : selectStmt.getResultSet()) {
            System.out.println(rec);
        }

        Assert.assertEquals(1, selectStmt.getResultSet().size());

        // Second Query Statement
        selectStmt = (SelectStatement) this.compile("SELECT a, b FROM t3 WHERE a = 2");
        this.execute(selectStmt);

        for (AbstractRecord rec : selectStmt.getResultSet()) {
            System.out.println(rec);
        }

        Assert.assertEquals(1, selectStmt.getResultSet().size());

        // Third Query Statement
        selectStmt = (SelectStatement) this.compile("SELECT a, b FROM t3 WHERE b = 1");
        this.execute(selectStmt);

        for (AbstractRecord rec : selectStmt.getResultSet()) {
            System.out.println(rec);
        }

        Assert.assertEquals(2, selectStmt.getResultSet().size());

        // Fourth Query Statement
        selectStmt = (SelectStatement) this.compile("SELECT a, b FROM t3 WHERE b = 2");
        this.execute(selectStmt);

        for (AbstractRecord rec : selectStmt.getResultSet()) {
            System.out.println(rec);
        }

        Assert.assertEquals(1, selectStmt.getResultSet().size());

        // Fifth Query Statement
        selectStmt = (SelectStatement) this.compile("SELECT a, b FROM t3 WHERE b = 3");
        this.execute(selectStmt);

        for (AbstractRecord rec : selectStmt.getResultSet()) {
            System.out.println(rec);
        }

        Assert.assertEquals(0, selectStmt.getResultSet().size());
    }

}
