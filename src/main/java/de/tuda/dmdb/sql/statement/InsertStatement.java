package de.tuda.dmdb.sql.statement;

import java.util.Vector;

import de.tuda.dmdb.access.AbstractTable;
import de.tuda.dmdb.access.AbstractUniqueIndex;
import de.tuda.dmdb.catalog.CatalogManager;
import de.tuda.dmdb.catalog.objects.Index;
import de.tuda.dmdb.catalog.objects.Table;
import de.tuda.dmdb.sql.error.Error;
import de.tuda.dmdb.sql.error.ErrorType;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;

@SuppressWarnings("rawtypes")
public class InsertStatement extends Statement {
	//for compilation
	private Table table;
	private Index pkIndex;
	private Vector<Constant> values = new Vector<Constant>();
	
	//for execution
	private AbstractTable tableData;
	private AbstractUniqueIndex pkIndexData;
	private AbstractRecord record;
	
	public Table getTable() {
		return table;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}
	
	public void addValue(Constant value){
		this.values.add(value);
	}

	public Vector<Constant> getValues() {
		return values;
	}

	@Override
	public Error compile() {
		//1. check if table  exists
		this.tableData = CatalogManager.getTable(this.table.getName());
		if(this.tableData==null){
			String[] args = {this.table.getName()};
			return new Error(ErrorType.TABLE_NOT_EXISTS, args );
		}
		
		//2. check if number of attribute fits to number of values
		this.record = this.tableData.getPrototype().clone();
		if(this.record.getValues().length != this.values.size()){
			String[] args = {this.table.getName(), ""+this.values.size()};
			return new Error(ErrorType.TABLE_NOT_EXISTS, args );
		}
		
		//create record
		int i=0;
		for(Constant constant: this.values){
			AbstractSQLValue value = this.record.getValue(i);
			value.parseValue(constant.getValue());
			this.record.setValue(i++, value);
		}
		
		//get index metatdata and data
		this.pkIndex = this.tableData.getPrimaryIndex();
		if(this.pkIndex!=null)
			this.pkIndexData = CatalogManager.getUniqueIndex(this.pkIndex.getName());
		
		return Error.NO_ERROR;
	}

	@Override
	public Error execute() {		
		//execute
		if(pkIndexData != null){
			boolean success = pkIndexData.insert(this.record);
			if(!success){
				String[] args = { this.tableData.getPrimaryKey().getName(), this.table.getName()};
				return new Error(ErrorType.INSERT_PK_VIOLATED, args );
			}
		}
		else{
			this.tableData.insert(this.record);
		}
		
		return Error.NO_ERROR;
	}
}
