package de.tuda.dmdb.sql.operator.exercise;

import java.util.Vector;

import de.tuda.dmdb.sql.operator.Operator;
import de.tuda.dmdb.sql.operator.ProjectionBase;
import de.tuda.dmdb.storage.AbstractRecord;

@SuppressWarnings("unused")
public class Projection extends ProjectionBase {
	
	public Projection(Operator child, Vector<Integer> attributes) {
		super(child, attributes);
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
		if(abstractRecord != null)
			abstractRecord.keepValues(attributes);
		return abstractRecord;
	}

	@Override
	public void close() {
		//TODO: implement this method
		child.close();
	}
}
