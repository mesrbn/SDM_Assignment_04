package de.tuda.dmdb.sql.operator.exercise;

import de.tuda.dmdb.sql.operator.EquiJoinBase;
import de.tuda.dmdb.sql.operator.Operator;
import de.tuda.dmdb.storage.AbstractRecord;

public class EquiJoin extends EquiJoinBase {
	
	public EquiJoin(Operator leftChild, Operator rightChild, int leftAtt, int rightAtt) {
		super(leftChild, rightChild, leftAtt, rightAtt);
	}
	
	@Override
	public void open() {
		//TODO: implement this method
	}

	@Override
	public AbstractRecord next() {
		//TODO: implement this method
		return null;
	}

	@Override
	public void close() {
		//TODO: implement this method
	}

}
