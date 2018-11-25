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
		child.open();
	}

	@Override
	public AbstractRecord next() {
		//TODO: implement this method

		AbstractRecord abstractRecord = child.next();
		if (abstractRecord != null) {
			if (abstractRecord.getValue(attribute).clone().equals(constant)) {
				return abstractRecord;
			}
			return next();
		}
		return null;
	}

	@Override
	public void close() {
		//TODO: implement this method
		child.close();
	}
}
