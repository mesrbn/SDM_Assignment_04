package de.tuda.dmdb.sql.operator.exercise;

import de.tuda.dmdb.sql.operator.Operator;
import de.tuda.dmdb.sql.operator.SelectionBase;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;

@SuppressWarnings("unused")
public class Selection extends SelectionBase {
	
	public Selection(Operator child, int attribute, AbstractSQLValue constant) {
		super(child, attribute, constant);
	}

	@Override
	public void open() {
		//TODO: implement this method
		System.out.println(child + " " + attribute + " " + constant);
	}

	@Override
	public AbstractRecord next() {
		//TODO: implement this method
		System.out.println(child + " " + attribute + " " + constant);
		return null;
	}

	@Override
	public void close() {
		//TODO: implement this method
		System.out.println(child + " " + attribute + " " + constant);
	}
}
