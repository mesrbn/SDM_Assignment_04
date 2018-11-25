package de.tuda.dmdb.sql.operator.exercise;

import de.tuda.dmdb.access.AbstractTable;
import de.tuda.dmdb.sql.operator.TableScanBase;
import de.tuda.dmdb.storage.AbstractRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("unused")
public class TableScan extends TableScanBase {
	
	public TableScan(AbstractTable table){
		super(table);
	}

	@Override
	public void open() {
		//TODO: implement this method
		tableIter = table.iterator();
	}

	@Override
	public AbstractRecord next() {
		//TODO: implement this method
		if (tableIter.hasNext()) {
			return tableIter.next();
		}
		return null;
	}

	@Override
	public void close() {
		//TODO: implement this method
		tableIter = null;
	}
}
